package ai.care.arcgraphqlsample.infrastructure;

import ai.care.arcgraphqlsample.domain.Project;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommonRepository {

    private static final List<Project> STORE = new ArrayList<>();

    public void add(Project obj) {
        STORE.add(obj);
    }

    public List<Project> list() {
        return STORE;
    }

}
