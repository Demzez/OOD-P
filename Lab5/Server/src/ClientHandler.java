import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ClientHandler extends Thread {
    private Socket clientSocket;
    private ObjectInputStream sois;
    private ObjectOutputStream soos;

    public ClientHandler(Socket socket, ObjectInputStream is, ObjectOutputStream os) {
        this.clientSocket = socket;
        this.sois = is;
        this.soos = os;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String clientMessageReceived = (String) sois.readObject();

                if (clientMessageReceived.equals("quit")) {
                    System.out.println("Client disconnected");
                    break;
                }

                System.out.println("Data received from client: " + clientMessageReceived);


                String response = calculateCost(clientMessageReceived);

                soos.writeObject(response);
                System.out.println("Calculation result sent to client: " + response);
            }
        } catch (Exception e) {
            System.out.println("Client connection lost");
        } finally {
            try {
                sois.close();
                soos.close();
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String calculateCost(String data) {
        try {

            String[] parts = data.split(" ");

            double materialCost = Double.parseDouble(parts[0]);
            double laborCost = Double.parseDouble(parts[1]);
            double additionalLaborCost = Double.parseDouble(parts[2]);
            double otherCosts = Double.parseDouble(parts[3]);

            // Расчет полной себестоимости
            double totalCost = materialCost + laborCost + additionalLaborCost + otherCosts;

            // Форматируем результат
            return String.format("Полная себестоимость: %.2f руб.\n" +
                            "Состав затрат:\n" +
                            "- Материалы: %.2f руб.\n" +
                            "- Основная зарплата: %.2f руб.\n" +
                            "- Дополнительная зарплата: %.2f руб.\n" +
                            "- Прочие затраты: %.2f руб.",
                    totalCost, materialCost, laborCost, additionalLaborCost, otherCosts);

        } catch (Exception e) {
            return "Ошибка: Неверный формат данных. Ожидается: materialCost,laborCost,additionalLaborCost,otherCosts";
        }
    }
}
