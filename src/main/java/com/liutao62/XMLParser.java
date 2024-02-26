package com.liutao62;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author liutao
 * @date 2024/1/11
 */
public class XMLParser {
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;
    public static String TEST_XML_STRING =
            "<?xml version=\"1.0\" ?><test attrib=\"moretest\">Turn this to JSON</test>";

    public static void main(String[] args) {
        try {
            JSONObject bdStaff = XML.toJSONObject(new FileReader("/Users/liutao/Downloads/bdstaff.xml"));

            JSONArray bdStaffAttributes = bdStaff.getJSONObject("components").getJSONObject("component").getJSONObject("class").getJSONObject("attributes").getJSONArray("attribute");

            HashMap<String, String> nameMap = Maps.newHashMap();
            bdStaffAttributes.forEach(attribute -> {
                String name = ((JSONObject) attribute).getString("name");
                String columnName = ((JSONObject) attribute).getString("columnName");
                nameMap.put(columnName, name);
            });

            JSONObject edStaff = XML.toJSONObject(new FileReader("/Users/liutao/Downloads/edstaff.xml"));
            JSONArray edStaffAttributes = edStaff.getJSONObject("components").getJSONObject("component").getJSONObject("class").getJSONObject("attributes").getJSONArray("attribute");

            ArrayList<Object> objects = Lists.newArrayList();
            Collection<String> values = nameMap.values();
            for (Object attribute : edStaffAttributes) {
                String name = ((JSONObject) attribute).getString("name");
                String columnName = ((JSONObject) attribute).getString("columnName");
                boolean remove = values.remove(name);
                if (!remove) {
                    String s = nameMap.get(columnName);
                    if (s != null) {
                        objects.add(s);
                        System.out.println("不匹配的属性: edStaffAttributeName " + name + " bdStaffAttributeName " + s + " columnName " + columnName);
                    }

                }
                nameMap.remove(columnName);
            }
            objects.addAll(nameMap.values());
//            String jsonPrettyPrintString = bdStaffAttributes.toString(PRETTY_PRINT_INDENT_FACTOR);
            System.out.println(nameMap);
            objects.forEach(System.out::println);
        } catch (JSONException je) {
            System.out.println(je.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
