package domain;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Row {
    private List<Column> columns;

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
    public Map<String , String> getColMap(){
        Map<String,String> map = new HashMap<String, String>();
        for (Column column:columns){
            map.put(column.getKey(), column.getValue());
        }
        return map;
    }

    public Column getColumn(String key){
        Column col = null;
        for (Column column : getColumns()){
            if (column.getKey().equalsIgnoreCase(key)) {
                col = column;
                break;
            }
        }
        return col;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject r = new JSONObject();
        for(Column gp: getColumns()){
            r.put(gp.getKey().toLowerCase(), gp.getValue());
        }
        return r;
    }
}
