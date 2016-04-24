package com.gordon.rawe.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by gordon on 16/4/23.
 */
public class DemoGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema(1,"com.gordon.rawe.models");
        Entity person = schema.addEntity("Person");
        person.addIdProperty();
        person.addStringProperty("name");
        person.addStringProperty("comment");
        Entity news = schema.addEntity("News");
        news.addIdProperty();
        news.addStringProperty("title");
        news.addStringProperty("content");
        try {
            new DaoGenerator().generateAll(schema, "../app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
