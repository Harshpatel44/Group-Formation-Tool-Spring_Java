package CSCI5308.GroupFormationTool.QuestionEditor;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

public class RankFunctionsService implements IRankFunctionsService {

    @Override
    public HashMap<Integer, String> arrangeOptionsBasedOnRank(String optionText, String rankText) {
        String[] optionList = optionText.split(",");
        String[] rankList = rankText.split(",");
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        for (int i = 0; i < optionList.length; i++) {
            map.put(Integer.valueOf(rankList[i]), optionList[i]);
        }
        Map<Integer, String> sorted = map
                .entrySet()
                .stream()
                .sorted(comparingByKey())
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(),
                                (e1, e2) -> e2, LinkedHashMap::new));
        return map;
    }
}
