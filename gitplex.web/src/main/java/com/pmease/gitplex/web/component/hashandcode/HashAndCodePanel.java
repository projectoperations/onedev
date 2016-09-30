package com.pmease.gitplex.web.component.hashandcode;

import javax.annotation.Nullable;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.pmease.gitplex.core.entity.Depot;
import com.pmease.gitplex.web.component.commithash.CommitHashPanel;
import com.pmease.gitplex.web.page.depot.file.DepotFilePage;

@SuppressWarnings("serial")
public class HashAndCodePanel extends Panel {

	private final IModel<Depot> depotModel;
	
	private final String hash;
	
	private final String path;
	
	public HashAndCodePanel(String id, IModel<Depot> depotModel, String hash) {
		this(id, depotModel, hash, null);
	}
	
	public HashAndCodePanel(String id, IModel<Depot> depotModel, String hash, @Nullable String path) {
		super(id);
		
		this.depotModel = depotModel;
		this.hash = hash;
		this.path = path;
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new CommitHashPanel("hash", hash));
		
		DepotFilePage.State state = new DepotFilePage.State();
		state.blobIdent.revision = hash;
		state.blobIdent.path = path;
		add(new BookmarkablePageLink<Void>("code", DepotFilePage.class, 
				DepotFilePage.paramsOf(depotModel.getObject(), state)));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(new HashAndCodeResourceReference()));
	}

	@Override
	protected void onDetach() {
		depotModel.detach();
		
		super.onDetach();
	}

}
