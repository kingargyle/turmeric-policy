/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.policy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.ebayopensource.turmeric.policy.adminui.client.Display;
import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.EntityHistory;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.HistoryChangeSummaryPresenter.HistoryChangeSummaryDisplay;
import org.ebayopensource.turmeric.policy.adminui.client.util.DateFormatUtil;
import org.ebayopensource.turmeric.policy.adminui.client.view.ErrorDialog;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.AbstractGenericView;
import org.ebayopensource.turmeric.policy.adminui.client.view.common.TurmericPager;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;

/**
 * The Class HistoryChangeSummaryView.
 */
public class HistoryChangeSummaryView extends AbstractGenericView implements HistoryChangeSummaryDisplay {
	
	private ScrollPanel scrollPanel;
	private FlowPanel mainPanel;
	private Display contentView;
	
	
	/**
	 * EntitySearchWidget
	 *
	 */
	private class EntitySearchWidget extends Composite {
	    private FlowPanel mainPanel;
	    private Button searchButton;
	    
	    private Label fromLabel;
	    private Label toLabel;
	    private Label entityLabel;
	    
        private DateBox fromText;
        private DateBox toText;
	    private ListBox entityBox;
	    
	    public EntitySearchWidget() {
	        mainPanel = new FlowPanel();
	        
	        Grid grid = new Grid(2, 6);
	   
	        fromLabel = new Label("From:");
	        fromText = new DateBox();
	        fromText.setValue(DateFormatUtil.resetTo12am(new Date()));
	        fromText.setFormat(DateFormatUtil.SHORT_DATE_FORMAT);
	        fromText.addStyleName("date-select-panel-item");
	        fromText.getDatePicker().addStyleName("turmeric");

	        toLabel = new Label("To:");
	        toText = new DateBox();
	        toText.setValue(DateFormatUtil.resetTo1159pm(new Date()));
	        toText.setFormat(DateFormatUtil.SHORT_DATE_FORMAT);
	        toText.addStyleName("date-select-panel-item");
	        toText.getDatePicker().addStyleName("turmeric");
	        
	        entityLabel = new Label("Entity:");
	        entityBox = new ListBox(false);
            searchButton = new Button(PolicyAdminUIUtil.policyAdminConstants.search());


            grid.setWidget(0, 0, fromLabel);
	        grid.setWidget(0, 1, fromText);

	        grid.setWidget(0, 2, toLabel);
	        grid.setWidget(0, 3, toText);


	        grid.setWidget(0, 4, entityLabel);
	        grid.setWidget(0, 5, entityBox);

	        grid.setWidget(1, 3, searchButton);
	        	        
	        mainPanel.add(grid);
	        initWidget(mainPanel);
	    }
	    
	    public void clear () {
	    	fromText.setValue(DateFormatUtil.resetTo12am(new Date(System.currentTimeMillis()-24*60*60*1000)));
	    	toText.setValue(DateFormatUtil.resetTo1159pm(new Date(System.currentTimeMillis())));
	    	entityBox.setSelectedIndex(-1);
	    }
	    
	    
	    public HasClickHandlers getSearchButton() {
	        return searchButton;
	    }
	    
	    public long getTo() {
	    	return (DateFormatUtil.resetTo1159pm(toText.getValue()).getTime());
	    }

	    public long getFrom() {
	    	return (DateFormatUtil.resetTo12am(fromText.getValue()).getTime());
	    }

	    public String getEntity() {
	        if (entityBox.getSelectedIndex() >= 0) {
				return entityBox.getItemText(entityBox.getSelectedIndex());
			} else {
				return null;
			}
	    }

	    public void setEntityTypes(List<String> entities) {
	        entityBox.clear();
	        for (String s:entities){
	        	entityBox.addItem(s);
	        }
	    }
	    

	}

	private class ContentView extends AbstractGenericView implements Display {
	    private FlowPanel mainPanel;
	    
	    private CellTable<EntityHistory> cellTable;
	    MultiSelectionModel<EntityHistory> selectionModel;
	    ProvidesKey<EntityHistory> keyProvider;
	    DisclosurePanel searchPanel;
	    EntitySearchWidget searchWidget;
	    List<EntityHistory> selections = new ArrayList<EntityHistory>();
	    ListDataProvider<EntityHistory> dataProvider;


	    public ContentView() {
	        mainPanel = new FlowPanel();

	        initWidget(mainPanel);

	        initialize();
	    }


	    public void setEntities(List<EntityHistory> entities) {
	        selections.clear();
	        cellTable.setRowCount(0);
	        List<EntityHistory> list;
	        if (entities == null) {
				list = Collections.emptyList();
			} else {
				list = entities;
			}
            
	        dataProvider.setList(list);
	        dataProvider.refresh();
	        cellTable.setSelectionModel(selectionModel);
	    }


	    public void activate() {
	        // do nothing for now
	    }

	    @Override
	    public void initialize() {
	        mainPanel.clear();
	        
	        //top part of contentPanel is a disclosure panel with a search feature
	        searchWidget = new EntitySearchWidget();
	        searchWidget.clear();
	        searchPanel = new DisclosurePanel(PolicyAdminUIUtil.policyAdminConstants.search());
	        searchPanel.setContent(searchWidget);

	        //bottom part of panel is a table with search results
	        Grid summaryGrid = new Grid (3, 1);
	        summaryGrid.setStyleName("entity_history_grid");


	        cellTable = new CellTable<EntityHistory>(keyProvider);
	        cellTable.setSelectionModel(selectionModel);
	        dataProvider = new ListDataProvider<EntityHistory>();
	        dataProvider.addDataDisplay(cellTable);
            TurmericPager pager = new TurmericPager();
            pager.setDisplay(cellTable);

	        //column for when
            Column<EntityHistory, Date> ehWhenCol = new Column<EntityHistory, Date>(
                    new DateCell(PolicyAdminUIUtil.tzTimeFormat)) {
                public Date getValue(EntityHistory history) {
                    return (history==null?null:history.getLastModifiedTime());
                }
            };
            cellTable.addColumn(ehWhenCol, PolicyAdminUIUtil.policyAdminConstants.ehWhenColumn());

	       //column for who
            TextColumn<EntityHistory> ehWhoCol = new TextColumn<EntityHistory> () {
	            public String getValue(EntityHistory history) {
                    return (history == null ? null : history.getLoginSubject());
	            }
	        };
	        cellTable.addColumn(ehWhoCol, PolicyAdminUIUtil.policyAdminConstants.ehWhoColumn());
	        
	       	       
	       //column for change type
	        TextColumn<EntityHistory> ehTypeCol = new TextColumn<EntityHistory> () {
	            public String getValue(EntityHistory history) {
	                if (history == null)
	                    return null;
	                return history.getAuditType();
	            }
	        };
	        cellTable.addColumn(ehTypeCol, PolicyAdminUIUtil.policyAdminConstants.ehChangeTypeColumn());
	       
	      //column for comments
	        
	        TextColumn<EntityHistory> ehCommentsCol = new TextColumn<EntityHistory> () {
	            public String getValue(EntityHistory history) {
	            	if (history == null)
	                    return null;
	                return history.getComments();
	            }
	        };
	        cellTable.addColumn(ehCommentsCol, PolicyAdminUIUtil.policyAdminConstants.ehCommentsColumn());

	        summaryGrid.setWidget(1, 0, cellTable);
	        summaryGrid.setWidget(2,0,pager);

	        mainPanel.addStyleName("sg-summary");
	        mainPanel.add(searchPanel);
	        searchPanel.addStyleName("sg-content");
	        summaryGrid.addStyleName("sg-content");
	        mainPanel.add(summaryGrid);
	    }
	}


	/**
	 * Instantiates a new history change summary view.
	 */
	public HistoryChangeSummaryView() {
	    scrollPanel = new ScrollPanel();
		mainPanel = new FlowPanel();
		scrollPanel.add(mainPanel);
		initWidget(scrollPanel);

		initialize();
	}

	/* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.view.common.AbstractGenericView#initialize()
	 */
	@Override
	public final void initialize() {
		mainPanel.clear();

		mainPanel.setWidth("100%");
		mainPanel.add(initContentView());
	}


	/**
	 * Inits the content view.
	 * 
	 * @return the widget
	 */
	protected final Widget initContentView() {
		ScrollPanel actionPanel = new ScrollPanel();
	    contentView = new ContentView();
	    actionPanel.add(contentView.asWidget());
	    return actionPanel;
	}
	

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.Display#activate()
	 */
	public void activate() {
		contentView.activate();
		this.setVisible(true);
	}


	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.policy.adminui.client.view.common.PolicyTemplateDisplay.PolicyPageTemplateDisplay#getContentView()
	 */
	public Display getContentView() {
		return contentView;
	}
	
    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.HistoryChangeSummaryPresenter.HistoryChangeSummaryDisplay#setEntities(java.util.List)
     */
    @Override
	public final void setEntities(final List<EntityHistory> entities) {
       ((ContentView)contentView).setEntities(entities);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.HistoryChangeSummaryPresenter.HistoryChangeSummaryDisplay#setEntityTypes(java.util.List)
     */
    @Override
	public final void setEntityTypes(final List<String> entityTypes) {
        ((ContentView)contentView).searchWidget.setEntityTypes(entityTypes);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.HistoryChangeSummaryPresenter.HistoryChangeSummaryDisplay#getSearchButton()
     */
    @Override
    public HasClickHandlers getSearchButton() {
        return ((ContentView) contentView).searchWidget.getSearchButton();
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.HistoryChangeSummaryPresenter.HistoryChangeSummaryDisplay#getFrom()
     */
    @Override
    public long getFrom() {
        return ((ContentView) contentView).searchWidget.getFrom();
    }


    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.HistoryChangeSummaryPresenter.HistoryChangeSummaryDisplay#getTo()
     */
    @Override
	public final long getTo() {
        return ((ContentView) contentView).searchWidget.getTo();
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.HistoryChangeSummaryPresenter.HistoryChangeSummaryDisplay#getEntity()
     */
    @Override
	public final String getEntity() {
        return ((ContentView) contentView).searchWidget.getEntity();
    }



    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.presenter.policy.HistoryChangeSummaryPresenter.HistoryChangeSummaryDisplay#error(java.lang.String)
     */
    public void error(final String msg) {
		ErrorDialog dialog = new ErrorDialog(true);
		dialog.setMessage(msg);
		dialog.getDialog().center();
		dialog.show();
	}
}
