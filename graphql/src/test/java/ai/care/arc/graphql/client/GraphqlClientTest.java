package ai.care.arc.graphql.client;

import com.alibaba.fastjson.TypeReference;
import ai.care.arc.graphql.GraphQLProvider;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GraphqlClient.class)
public class GraphqlClientTest {


    @Test
    public void execute_ql() throws Exception {
        GraphqlClient graphqlClient = PowerMockito.spy(new GraphqlClient(new GraphQLProvider()));

        Item item = new Item();
        item.setId("0x123");
        item.setName("zhang");
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("subjects", Collections.singletonList(item));
        PowerMockito.doReturn(map).when(graphqlClient, "doExecute", any());

        Type type = new TypeReference<List<Item>>() {}.getType();
        List<Item> result = graphqlClient.execute("{" +
                "  subject {" +
                "    id" +
                "    name" +
                "  }" +
                "}", type);
        assertNull(result);
        result = graphqlClient.execute("{" +
                "  subjects {" +
                "    id" +
                "    name" +
                "  }" +
                "}", type);

        assertTrue(result.size() > 0);
        assertNotNull(result.get(0).getId());
        assertNotNull(result.get(0).getName());
        assertNull(result.get(0).getAge());
    }

    @Data
    static class Item {
        private String id;
        private String name;
        private Integer age;
    }
}