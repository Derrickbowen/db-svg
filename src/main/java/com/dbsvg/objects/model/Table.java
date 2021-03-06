/*
 * DB-SVG Copyright 2012 Derrick Bowen
 *
 * This file is part of DB-SVG.
 *
 *   DB-SVG is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   DB-SVG is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with DB-SVG.  If not, see <http://www.gnu.org/licenses/>.
 *   
 *   @author Derrick Bowen derrickbowen@dbsvg.com
 */
package com.dbsvg.objects.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import com.dbsvg.common.HashCodeUtil;
import com.dbsvg.objects.view.SchemaPage;
import com.dbsvg.objects.view.TableView;

@SuppressWarnings("serial")
public class Table implements Comparable<Table>, Serializable {

	private UUID id;
	private int viewId;
	private String name = "";
	private String schemaId = "";
	private Map<String, Column> columns = new LinkedHashMap<String, Column>();
	private Map<String, ForeignKey> foreignKeys = new LinkedHashMap<String, ForeignKey>();
	private Map<String, Table> referencingTables = new LinkedHashMap<String, Table>();
	private Map<String, PrimaryKey> primaryKeys = new LinkedHashMap<String, PrimaryKey>();
	// by page id
	private Map<UUID, TableView> tablePageViews = new LinkedHashMap<UUID, TableView>();
	private int width = 0;
	private int height = 0;

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

	public void setViewId(int viewId) {
		this.viewId = viewId;
	}

	public int getViewId() {
		return viewId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchemaId() {
		return schemaId;
	}

	public void setSchemaId(String schemaId) {
		this.schemaId = schemaId;
	}

	/**
	 * Map key = column name value = column object
	 * 
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

	public Map<UUID, TableView> getTablePageViews() {
		return tablePageViews;
	}

	/**
	 * I don't think we really want the TableViews to be totally reset.
	 * 
	 * @param tablePageViews
	 */
	// private void setTablePageViews(Map<UUID, TableView> tableViews) {
	// this.tablePageViews = tableViews;
	// }

	/**
	 * Convenience method, Adds a TableView for the Given Page id.
	 * 
	 * @param tv
	 * @param pageId
	 */
	public void addTableViewForPage(TableView tv, UUID pageId) {
		tablePageViews.put(pageId, tv);
	}

	/**
	 * Convenience method, Adds a TableView for the Given Page.
	 * 
	 * @param tv
	 * @param pageId
	 */
	public void addTableViewForPage(TableView tv, SchemaPage page) {
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
		if (o == null) {
			return 1;
		}
		return this.name.compareTo(o.getName());
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!o.getClass().equals(Table.class)) {
			return false;
		}
		Table t = (Table) o;
		return compareTo(t) == 0;
	}

	@Override
	public int hashCode() {
		return HashCodeUtil.generateHash(name);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Table[");
		builder.append(getName());
		builder.append(",id:");
		builder.append(getId());
		builder.append(']');
		return builder.toString();
	}
}
