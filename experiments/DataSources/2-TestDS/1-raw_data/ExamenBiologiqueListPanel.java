package org.imogene.epicam.client.ui.table.panel;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.EpicamIconConstants;
import org.imogene.epicam.client.event.list.*;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.client.ui.table.ExamenBiologiqueDynaTable;
import org.imogene.epicam.client.dataprovider.ExamenBiologiqueDataProvider;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.web.client.css.ImogResources;
import org.imogene.web.client.event.GoHomeEvent;
import org.imogene.web.client.event.IsTableFilteredEvent;
import org.imogene.web.client.event.SelectMenuItemEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.panel.WrapperPanelForTable;
import org.imogene.web.client.ui.table.filter.ImogFilterPanel;
import org.imogene.web.client.ui.widget.PopupButton;
import org.imogene.web.client.ui.widget.SimpleMenuItem;
import org.imogene.web.client.util.LocalSession;
import org.imogene.web.client.util.TokenHelper;
import org.imogene.web.shared.proxy.criteria.ImogJunctionProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * Panel that contains the ExamenBiologique Dynatable
 * @author Medes-IMPS
 */
public class ExamenBiologiqueListPanel extends Composite {

	private static final Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, ExamenBiologiqueListPanel> {
	}

	private static List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();
	private final EpicamRequestFactory requestFactory;
	private PopupButton filterButton;
	private PopupButton plusButton;
	private PopupButton listButton;
	private PushButton goHomeButton;

	@UiField(provided = true)
	ImogResources imogResources;

	@UiField(provided = true)
	WrapperPanelForTable wrapperPanel;
	@UiField(provided = true)
	ExamenBiologiqueDynaTable listComposite;

	/**
	 * Constructor
	 * @param requestFactory
	 * @param searchText text that will be used to filter the table entries
	 */
	public ExamenBiologiqueListPanel(EpicamRequestFactory requestFactory,
			String searchText) {

		this.requestFactory = requestFactory;
		imogResources = GWT.create(ImogResources.class);
		imogResources.imogStyle().ensureInjected();

		/* wrapper panel */
		wrapperPanel = new WrapperPanelForTable();
		wrapperPanel.setTitle(NLS.constants().examenBiologique_table_text());
		if (EpicamIconConstants.EXAMENBIOLOGIQUE_ICON != null)
			wrapperPanel.setIcon(EpicamIconConstants.EXAMENBIOLOGIQUE_ICON);

		/* data provider */
		ExamenBiologiqueDataProvider provider = new ExamenBiologiqueDataProvider(
				requestFactory);
		String filteringMessage = configureDataProvider(provider, searchText);
		if (filteringMessage != null)
			wrapperPanel.setMessageLabel(filteringMessage);

		/* dynatable */
		if (AccessManager.canDeleteExamenBiologique()
				&& AccessManager.canEditExamenBiologique())
			listComposite = new ExamenBiologiqueDynaTable(requestFactory,
					provider, true);
		else
			listComposite = new ExamenBiologiqueDynaTable(requestFactory,
					provider, false);

		configureWrapperPanelForTable();
		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * Constructor
	 * @param requestFactory
	 */
	public ExamenBiologiqueListPanel(EpicamRequestFactory requestFactory) {
		this(requestFactory, null);
	}

	/**
	 * Configures the Data provider that enables to get the data to feed the table that lists ExamenBiologique entries
	 * @param provider
	 * @param searchText
	 * @return
	 */
	private String configureDataProvider(ExamenBiologiqueDataProvider provider,
			String searchText) {

		String filteringMessage = null;
		if (searchText != null) {
			filteringMessage = provider.fullTextSearch(searchText);
			LocalSession.get().setSearchCriterions(
					provider.getSearchCriterions(), filteringMessage);
		} else {
			ImogJunctionProxy searchCriterions = LocalSession.get()
					.getSearchCriterions();
			if (searchCriterions != null) {
				provider.setSearchCriterions(searchCriterions);
				filteringMessage = LocalSession.get().getFilteringMessage();
			}
		}
		return filteringMessage;
	}

	/**
	 * Configures the Wrapper panel to manage the actions that are related to the table that lists ExamenBiologique entries
	 */
	private void configureWrapperPanelForTable() {

		ImogFilterPanel filterPanel = listComposite.getFilterPanel();
		Command createCommand = listComposite.getCreateCommand();
		Command exportButton = listComposite.getCsvExportButton();
		PushButton deleteButton = listComposite.getDeleteButton();

		// add pager
		wrapperPanel.addHeaderWidget(listComposite.getTablePager());

		// add filter panel
		setFilterButton(filterPanel);

		// add create and export buttons
		setOtherActions(createCommand, exportButton);

		// add delete button
		if (deleteButton != null)
			wrapperPanel.addHeaderWidget(deleteButton);

		// add goHome button
		wrapperPanel.addHeaderWidget(goHomeButton());
	}

	/**
	 * Adds a Filter button that enables to filter the table entries to the wrapper panel
	 * @param eventBus
	 */
	private void setFilterButton(ImogFilterPanel filterPanel) {

		if (filterPanel != null) {
			filterButton = new PopupButton(BaseNLS.constants().button_filter());
			filterButton.addPopupPanelContent(filterPanel);
			wrapperPanel.addHeaderWidget(filterButton);
		}
	}

	/**
	 * Adds create and export buttons to the wrapper panel
	 * @param createCommand
	 * @param exportButton
	 */
	private void setOtherActions(Command createCommand, Command exportCommand) {

		if (createCommand != null || exportCommand != null) {

			plusButton = new PopupButton(BaseNLS.constants().button_plus());

			if (createCommand != null) {
				SimpleMenuItem item = new SimpleMenuItem(
						requestFactory.getEventBus(), BaseNLS.constants()
								.button_create(), createCommand);
				plusButton.addPopupPanelContent(item);
			}

			if (exportCommand != null) {
				SimpleMenuItem item = new SimpleMenuItem(
						requestFactory.getEventBus(), BaseNLS.constants()
								.button_export(), exportCommand);
				plusButton.addPopupPanelContent(item);
			}

			wrapperPanel.addHeaderWidget(plusButton);
		}
	}

	/**
	 * 
	 * @param eventBus
	 */
	private void setListActions() {

		listButton = new PopupButton(BaseNLS.constants().button_list());

		if (AccessManager.canDirectAccessPatient()
				&& AccessManager.canReadPatient()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/patient/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.patient_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessCasTuberculose()
				&& AccessManager.canReadCasTuberculose()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/castuberculose/",
							true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.casTuberculose_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessTransfertReference()
				&& AccessManager.canReadTransfertReference()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST
							+ "/transfertreference/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.transfertReference_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessRegime()
				&& AccessManager.canReadRegime()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/regime/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.regime_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessExamenATB()
				&& AccessManager.canReadExamenATB()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/examenatb/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.examenATB_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessExamenMicroscopie()
				&& AccessManager.canReadExamenMicroscopie()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(
							TokenHelper.TK_LIST + "/examenmicroscopie/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.examenMicroscopie_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessExamenSerologie()
				&& AccessManager.canReadExamenSerologie()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/examenserologie/",
							true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.examenSerologie_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessLot() && AccessManager.canReadLot()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/lot/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.lot_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessCommande()
				&& AccessManager.canReadCommande()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/commande/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.commande_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessReception()
				&& AccessManager.canReadReception()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/reception/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.reception_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessRavitaillement()
				&& AccessManager.canReadRavitaillement()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/ravitaillement/",
							true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.ravitaillement_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessInventaire()
				&& AccessManager.canReadInventaire()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/inventaire/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.inventaire_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessHorsUsage()
				&& AccessManager.canReadHorsUsage()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/horsusage/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.horsUsage_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessEntreeLot()
				&& AccessManager.canReadEntreeLot()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/entreelot/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.entreeLot_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessSortieLot()
				&& AccessManager.canReadSortieLot()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/sortielot/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.sortieLot_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessSmsPredefini()
				&& AccessManager.canReadSmsPredefini()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/smspredefini/",
							true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.smsPredefini_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessOutBox()
				&& AccessManager.canReadOutBox()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/outbox/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.outBox_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessFormation()
				&& AccessManager.canReadFormation()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/formation/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.formation_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessTutoriel()
				&& AccessManager.canReadTutoriel()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/tutoriel/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.tutoriel_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessRegion()
				&& AccessManager.canReadRegion()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/region/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.region_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessDistrictSante()
				&& AccessManager.canReadDistrictSante()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/districtsante/",
							true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.districtSante_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessCentreDiagTrait()
				&& AccessManager.canReadCentreDiagTrait()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/centrediagtrait/",
							true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.centreDiagTrait_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessLaboratoireReference()
				&& AccessManager.canReadLaboratoireReference()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST
							+ "/laboratoirereference/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.laboratoireReference_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessLieuDit()
				&& AccessManager.canReadLieuDit()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/lieudit/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.lieuDit_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessMedicament()
				&& AccessManager.canReadMedicament()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/medicament/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.medicament_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessIntrant()
				&& AccessManager.canReadIntrant()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/intrant/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.intrant_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessPersonnel()
				&& AccessManager.canReadPersonnel()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/personnel/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.personnel_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessDepartPersonnel()
				&& AccessManager.canReadDepartPersonnel()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/departpersonnel/",
							true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.departPersonnel_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessArriveePersonnel()
				&& AccessManager.canReadArriveePersonnel()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/arriveepersonnel/",
							true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.arriveePersonnel_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessUtilisateur()
				&& AccessManager.canReadUtilisateur()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/utilisateur/", true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.utilisateur_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		if (AccessManager.canDirectAccessQualification()
				&& AccessManager.canReadQualification()) {

			Command command = new Command() {
				public void execute() {
					LocalSession.get().setSearchCriterions(null, null);
					History.newItem(TokenHelper.TK_LIST + "/qualification/",
							true);
				}
			};
			SimpleMenuItem item = new SimpleMenuItem(
					requestFactory.getEventBus(), NLS.constants()
							.qualification_name_plur(), command);
			listButton.addPopupPanelContent(item);
		}

		wrapperPanel.addHeaderWidget(listButton);
	}

	/**
	 * Adds a button that enables to go the the Home panel
	 * @param eventBus
	 * @return
	 */
	private PushButton goHomeButton() {

		goHomeButton = new PushButton(BaseNLS.constants().button_home());
		goHomeButton.setStyleName(imogResources.imogStyle().imogButton());
		goHomeButton.addStyleName(imogResources.imogStyle().imogButton2());
		goHomeButton.addStyleName("Dynatable-Button");
		return goHomeButton;
	}

	/**
	 * 
	 */
	private void setButtonHandlers() {

		final EventBus eventBus = requestFactory.getEventBus();

		// Handler for filterButton
		if (filterButton != null) {
			registrations.add(eventBus.addHandler(IsTableFilteredEvent.TYPE,
					new IsTableFilteredEvent.Handler() {
						@Override
						public void noticeFilteringChange(Boolean isFiltered,
								String message) {
							if (isFiltered) {
								filterButton.setButtonActivatedStyle();
								wrapperPanel.setMessageLabel(message);
							} else {
								filterButton.removeButtonActivatedStyle();
								wrapperPanel.clearMessageLabel();
							}
						}
					}));
		}

		// Handler for plusButton
		if (plusButton != null) {
			registrations.add(eventBus.addHandler(SelectMenuItemEvent.TYPE,
					new SelectMenuItemEvent.Handler() {
						@Override
						public void selectMenuItem(SimpleMenuItem menuItem) {
							plusButton.hidePopup();
						}
					}));
		}

		// Handler for listButton		
		if (listButton != null) {
			registrations.add(eventBus.addHandler(SelectMenuItemEvent.TYPE,
					new SelectMenuItemEvent.Handler() {
						@Override
						public void selectMenuItem(SimpleMenuItem menuItem) {
							listButton.hidePopup();
						}
					}));
		}

		// Handler for goHomeButton	
		if (goHomeButton != null) {
			registrations.add(goHomeButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					History.newItem(TokenHelper.TK_CLASSIC, true);
				}
			}));
		}

	}

	@Override
	protected void onUnload() {
		for (HandlerRegistration r : registrations)
			r.removeHandler();
		registrations.clear();
		super.onUnload();
	}

	@Override
	protected void onLoad() {
		setButtonHandlers();
		super.onLoad();
	}

}
