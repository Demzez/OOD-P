package patterns;

import model.Medicine;
import java.util.List;

public interface SearchStrategy {
    List<Medicine> search(List<Medicine> medicines, String query);
}

