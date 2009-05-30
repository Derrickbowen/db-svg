package DBViewer.objects.model;

import java.util.*;
import DBViewer.objects.view.*;
import java.io.Serializable;

/**
 *
 * @author Derrick Bowen <derrickbowen@gmail.com>
 */
public class Table implements Comparable<Table>,Serializable{

   UUID id;
   String name = "";
   String schemaName = "";
   Map<String, Column> columns = new LinkedHashMap();
   Map<String, ForeignKey> foreignKeys = new LinkedHashMap();
   Map<String, Table> referencingTables = new LinkedHashMap();
   Map<String, PrimaryKey> primaryKeys = new LinkedHashMap();
   // by page id
   Map<UUID, TableView> tablePageViews = new LinkedHashMap();
   int width = 0;
   int height = 0;


   /**
    * 
    */
   public Table() {
       this.id = UUID.randomUUID();
   }

   public Table(String name) {
      this.name = name;
       this.id = UUID.randomUUID();
   }

   public UUID getId() {
       return id;
   }

    public void setId(UUID id) {
        this.id = id;
    }


   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

   /**
    * Map key = column name
    * value = column object
    * @return
    */
   public Map<String, Column> getColumns() {
      return columns;
   }

   /**
    * 
    * @param columns
    */
   public void setColumns(Map<String, Column> columns) {
      this.columns = columns;
   }

   public Map<String, ForeignKey> getForeignKeys() {
      return foreignKeys;
   }

   public void setForeignKeys(Map<String, ForeignKey> foreignKeys) {
      this.foreignKeys = foreignKeys;
   }

   public Map<String, Table> getReferencingTables() {
      return referencingTables;
   }

   public void setReferencingTables(Map<String, Table> referencingTables) {
      this.referencingTables = referencingTables;
   }

   public Map<String, PrimaryKey> getPrimaryKeys() {
      return primaryKeys;
   }

   public void setPrimaryKeys(Map<String, PrimaryKey> primaryKeys) {
      this.primaryKeys = primaryKeys;
   }
    public int generateWidth() {
        if (width<1) {
            width = name.length();
            for (Column c : columns.values()) {
                int clen = c.getName().length();
                if (clen > width) {
                    width = clen;
                }
            }
        }
        return width;
    }

    public Map<UUID, TableView> getTablePageViews() {
        return tablePageViews;
    }

    /**
     * I don't think we really want the TableViews to be totally reset.
     *
     * @param tablePageViews
     */
    private void setTablePageViews(Map<UUID, TableView> tableViews) {
        this.tablePageViews = tableViews;
    }

    /**
     * Convenience method, Adds a TableView for the Given Page id.
     * @param tv
     * @param pageId
     */
    public void addTableViewForPage(TableView tv, UUID pageId){
        tablePageViews.put(pageId, tv);
    }

    /**
     * Convenience method, Adds a TableView for the Given Page.
     * @param tv
     * @param pageId
     */
    public void addTableViewForPage(TableView tv, SchemaPage page){
        addTableViewForPage(tv, page.getId());
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int compareTo(Table o) {
        return this.name.compareTo(o.getName());
    }
}