package patterns;

import model.Medicine;

import java.util.List;

public class CategorySearchStrategy implements SearchStrategy {
    @Override
    public List<Medicine> search(List<Medicine> medicines, String query) {
        return medicines.stream()
                .filter(medicine -> medicine.getCategory().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }
}
