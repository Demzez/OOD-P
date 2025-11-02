package patterns;

import model.Medicine;

import java.util.List;

// Конкретные стратегии поиска
public class NameSearchStrategy implements SearchStrategy {
    @Override
    public List<Medicine> search(List<Medicine> medicines, String query) {
        return medicines.stream()
                .filter(medicine -> medicine.getName().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }
}
