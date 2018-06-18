package org.imogene.epicam.client.ui.panel;

import java.util.ArrayList;
import java.util.List;

import org.imogene.admin.client.AdminIconConstants;
import org.imogene.admin.client.event.create.CreateCardEntityEvent;
import org.imogene.admin.client.event.create.CreateFieldGroupEvent;
import org.imogene.admin.client.event.create.CreateNotificationEvent;
import org.imogene.admin.client.event.create.CreateProfileEvent;
import org.imogene.admin.client.event.list.ListCardEntityEvent;
import org.imogene.admin.client.event.list.ListFieldGroupEvent;
import org.imogene.admin.client.event.list.ListNotificationEvent;
import org.imogene.admin.client.event.list.ListProfileEvent;
import org.imogene.admin.client.i18n.AdminNLS;
import org.imogene.web.client.dynamicfields.i18n.DynFieldsNLS;
import org.imogene.web.client.event.CreateDynamicFieldTemplateEvent;
import org.imogene.web.client.event.GoHomeEvent;
import org.imogene.web.client.event.ListDynamicFieldTemplateEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.field.group.FieldGroupPanel;
import org.imogene.web.client.ui.panel.EntityPanel;
import org.imogene.web.client.util.ProfileUtil;
import org.imogene.web.client.util.LocalSession;
import org.imogene.web.client.util.TokenHelper;
import org.imogene.web.client.util.TokenHelper.EntityToken;
import org.imogene.epicam.client.ui.panel.SmsPanel;
import org.imogene.epicam.client.ui.panel.RapportPanel;
import org.imogene.epicam.client.event.view.ViewEpicamMapEvent;
import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamIconConstants;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.client.event.create.*;
import org.imogene.epicam.client.event.list.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * Home panel of the application
 * @author Medes-IMPS
 */
public class HomePanel extends Composite {

	private static int PATIENT_HLK = 0;
	private static int EXAM_HLK = 1;
	private static int MAP_HLK = 2;
	private static int ACMS_HLK = 3;
	private static int FORMATION_HLK = 4;
	private static int STOCK_HLK = 5;
	private static int ADMINISTRATION_HLK = 6;
	private static int MEDICAMENTS_HLK = 7;
	private static int RESRCHUM_HLK = 8;
	private static int HELP_HLK = 9;

	interface Binder extends UiBinder<Widget, HomePanel> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);
	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private EventBus eventBus;

//	@UiField
//	StackPanel stackPanel;
	@UiField
	ScrollPanel scrollPanel;

	/* thema  Help */
	@UiField(provided = true)
	FieldGroupPanel temaHelp;
	@UiField(provided = true)
	HTML helpMessage;

	/* thema  Users */
	@UiField(provided = true)
	FieldGroupPanel temaUsers;
	@UiField(provided = true)
	EntityPanel personnel;
	@UiField(provided = true)
	EntityPanel utilisateur;

	/* thema  Administration */
	@UiField(provided = true)
	FieldGroupPanel temaAdministration;
	@UiField(provided = true)
	EntityPanel notification;
	@UiField(provided = true)
	EntityPanel dynamicField_Template;
	@UiField(provided = true)
	EntityPanel profile;

	/* thema Model */
	@UiField(provided = true)
	FieldGroupPanel temaModel;
	@UiField(provided = true)
	EntityPanel cardEntity;
	@UiField(provided = true)
	EntityPanel fieldGroup;

	@UiField(provided = true)
	HTML homeMessage;

	/**
	 * 
	 * @param eventBus
	 */
	public HomePanel(EventBus eventBus) {

		this.eventBus = eventBus;

		homeMessage = new HTML(BaseNLS.messages().homePanel_message());

		helpMessage = new HTML(BaseNLS.messages().help_content());
		//Specific help for each component
		//		patientHelp = new HTML(BaseNLS.messages().patient_help());
		//		examHelp = new HTML(BaseNLS.messages().exam_help());
		//		smsHelp = new HTML(BaseNLS.messages().sms_help());
		//		trainingHelp = new HTML(BaseNLS.messages().training_help());
		//		stockHelp = new HTML(BaseNLS.messages().stock_help());
		//		adminHelp = new HTML(BaseNLS.messages().admin_help());
		//		drugAdminHelp = new HTML(BaseNLS.messages().drug_admin_help());
		//		reportHelp = new HTML(BaseNLS.messages().report_help());
		//		userAdminHelp = new HTML(BaseNLS.messages().user_admin_help());

		/* thema  Users */
		temaUsers = new FieldGroupPanel();
		temaUsers.setGroupTitle(AdminNLS.constants().thema_users());

		if (EpicamIconConstants.PERSONNEL_ICON != null)
			personnel = new EntityPanel(NLS.constants().personnel_name(),
					EpicamIconConstants.PERSONNEL_ICON);
		else
			personnel = new EntityPanel(NLS.constants().personnel_name());

		if (EpicamIconConstants.UTILISATEUR_ICON != null)
			utilisateur = new EntityPanel(NLS.constants().utilisateur_name(),
					EpicamIconConstants.UTILISATEUR_ICON);
		else
			utilisateur = new EntityPanel(NLS.constants().utilisateur_name());

		/* thema  Administration */
		temaAdministration = new FieldGroupPanel();
		temaAdministration.setGroupTitle(AdminNLS.constants()
				.thema_administration());
		notification = new EntityPanel(
				AdminNLS.constants().notification_name(),
				AdminIconConstants.NOTIFICATION_ICON);
		dynamicField_Template = new EntityPanel(DynFieldsNLS.constants()
				.dynamicField_Template_name(),
				AdminIconConstants.DYNAMICFIELD_TEMPLATE_ICON);
		profile = new EntityPanel(AdminNLS.constants().profile_name());

		/* thema Model */
		temaModel = new FieldGroupPanel();
		temaModel.setGroupTitle(AdminNLS.constants().thema_model());
		cardEntity = new EntityPanel(AdminNLS.constants().cardEntity_name());
		fieldGroup = new EntityPanel(AdminNLS.constants().fieldGroup_name());

		/* thema  Help */
		temaHelp = new FieldGroupPanel();
		temaHelp.setGroupTitle(BaseNLS.messages().thema_Help());
		initWidget(BINDER.createAndBindUi(this));

		configMenus();
		setVisibility();

		int height = Window.getClientHeight();
		setPanelContentHeight(height - height / 5);
	}

	/**
	 * Configures the menu buttons
	 */
	private void configMenus() {

		/* Personnel Menu */

		if (AccessManager.canCreatePersonnel()
				&& AccessManager.canEditPersonnel()) {
			personnel.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/personnel/", true);
				}
			});
		} else
			personnel.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessPersonnel()
				&& AccessManager.canReadPersonnel()) {
			personnel.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/personnel/", true);
				}
			});
			personnel.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = personnel.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/personnel/"
								+ searchValue, true);
				}
			});
		} else {
			personnel.setListClickHandler(null);
			personnel.setSearchClickHandler(null);
		}

		/* Utilisateur Menu */

		if (AccessManager.canCreateUtilisateur()
				&& AccessManager.canEditUtilisateur()) {
			utilisateur.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/utilisateur/", true);
				}
			});
		} else
			utilisateur.setCreateClickHandler(null);

		if (AccessManager.canDirectAccessUtilisateur()
				&& AccessManager.canReadUtilisateur()) {
			utilisateur.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/utilisateur/", true);
				}
			});
			utilisateur.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = utilisateur.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST + "/utilisateur/"
								+ searchValue, true);
				}
			});
		} else {
			utilisateur.setListClickHandler(null);
			utilisateur.setSearchClickHandler(null);
		}

		/* Notification Menu */

		if (ProfileUtil.isAdmin()) {
			notification.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/notification/", true);
				}
			});
			notification.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/notification/",
							true);
				}
			});
			notification.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = notification.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/notification/searchValue", true);
				}
			});
		} else {
			notification.setCreateClickHandler(null);
			notification.setListClickHandler(null);
			notification.setSearchClickHandler(null);
		}

		/* DynamicFieldTemplate Menu */

		if (ProfileUtil.isAdmin()) {
			dynamicField_Template.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW
							+ "/dynamicfieldtemplate/", true);
				}
			});
			dynamicField_Template.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST
							+ "/dynamicfieldtemplate/", true);
				}
			});
			dynamicField_Template.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = dynamicField_Template.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/dynamicfieldtemplate/searchValue", true);
				}
			});
		} else {
			dynamicField_Template.setCreateClickHandler(null);
			dynamicField_Template.setListClickHandler(null);
			dynamicField_Template.setSearchClickHandler(null);
		}

		/* FieldGroup Menu */

		if (ProfileUtil.isAdmin()) {
			fieldGroup.setCreateClickHandler(null);

			fieldGroup.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/fieldgroup/", true);
				}
			});
			fieldGroup.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = fieldGroup.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/fieldgroup/searchValue", true);
				}
			});
		} else {
			fieldGroup.setCreateClickHandler(null);
			fieldGroup.setListClickHandler(null);
			fieldGroup.setSearchClickHandler(null);
		}

		/* Profile Menu */

		if (ProfileUtil.isAdmin()) {
			profile.setCreateClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_NEW + "/profile/", true);
				}
			});
			profile.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/profile/", true);
				}
			});
			profile.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = profile.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/profile/searchValue", true);
				}
			});
		} else {
			profile.setCreateClickHandler(null);
			profile.setListClickHandler(null);
			profile.setSearchClickHandler(null);
		}

		/* CardEntity Menu */

		if (ProfileUtil.isAdmin()) {
			cardEntity.setCreateClickHandler(null);
			cardEntity.setListClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/cardentity/", true);
				}
			});
			cardEntity.setSearchClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String searchValue = cardEntity.getSearchValue();
					if (searchValue != null && !searchValue.isEmpty())
						History.newItem(TokenHelper.TK_LIST
								+ "/cardentity/searchValue", true);
				}
			});
		} else {
			cardEntity.setCreateClickHandler(null);
			cardEntity.setListClickHandler(null);
			cardEntity.setSearchClickHandler(null);
		}

	}

	/**
	 * Set the menu visibility
	 */
	private void setVisibility() {

		/* thema  Users */
		boolean showUsersTema = false;
		if (!AccessManager.canDirectAccessPersonnel()
				|| !AccessManager.canReadPersonnel())
			personnel.setVisible(false);
		else
			showUsersTema = true;
		if (!AccessManager.canDirectAccessUtilisateur()
				|| !AccessManager.canReadUtilisateur())
			utilisateur.setVisible(false);
		else
			showUsersTema = true;
		if (!showUsersTema)
			temaUsers.setVisible(false);

		/* thema  Administration */
		temaAdministration.setVisible(true);
		/* no notification defined in model, panel hidden */
		notification.setVisible(false);

		/* no dynamic field defined in model, panel hidden */
		dynamicField_Template.setVisible(false);

		profile.setVisible(true);

		/* thema Model */
		cardEntity.setVisible(true);
		fieldGroup.setVisible(true);
		temaModel.setVisible(true);

	}

	/**
	 * Sets the height of the panel that contents the entity menus
	 * @param height
	 */
	public void setPanelContentHeight(int height) {
		scrollPanel.getElement().getStyle().clearHeight();
		scrollPanel.getElement().getStyle()
				.setProperty("height", height + "px");
	}

	/**
	 * 
	 */
	private void setWindowHandler() {
		registrations.add(Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent arg0) {
				int height = Window.getClientHeight();
				setPanelContentHeight(height - height / 5);
			}
		}));
	}

	@Override
	protected void onLoad() {
		setWindowHandler();
		super.onLoad();
	}

	@Override
	protected void onUnload() {
		for (HandlerRegistration r : registrations)
			r.removeHandler();
		registrations.clear();
		super.onUnload();
	}

}
