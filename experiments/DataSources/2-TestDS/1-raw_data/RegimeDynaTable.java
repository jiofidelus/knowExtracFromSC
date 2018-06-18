package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateRegimeEvent;
import org.imogene.epicam.client.event.list.ListRegimeEvent;
import org.imogene.epicam.client.event.view.ViewRegimeEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.RegimeFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.RegimeProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.RegimeRequest;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.web.client.event.SelectionChangedInTableEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.table.ImogBeanDataProvider;
import org.imogene.web.client.ui.table.ImogColumn;
import org.imogene.web.client.ui.table.ImogDynaTable;
import org.imogene.web.client.ui.table.filter.ImogFilterPanel;
import org.imogene.web.client.util.BooleanUtil;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.client.util.TokenHelper;
import org.imogene.epicam.shared.constants.EpicamBirtConstants;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PushButton;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * Composite that displays the list of Regime entries
 * @author MEDES-IMPS
 */
public class RegimeDynaTable extends ImogDynaTable<RegimeProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public RegimeDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<RegimeProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new RegimeFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadRegimeDescription()) {
			Column<RegimeProxy, String> nomColumn = new NomColumn();
			nomColumn.setSortable(true);
			table.addColumn(nomColumn, NLS.constants().regime_field_s_nom());
		}
		if (AccessManager.canReadRegimeDescription()) {
			Column<RegimeProxy, String> typeColumn = new TypeColumn();
			typeColumn.setSortable(true);
			table.addColumn(typeColumn, NLS.constants().regime_field_s_type());
		}
		if (AccessManager.canReadRegimeDescription()) {
			Column<RegimeProxy, String> dureeTraitementColumn = new DureeTraitementColumn();
			dureeTraitementColumn.setSortable(true);
			table.addColumn(dureeTraitementColumn, NLS.constants()
					.regime_field_s_dureeTraitement());
		}
		if (AccessManager.canReadRegimeDescription()) {
			Column<RegimeProxy, String> poidsMinColumn = new PoidsMinColumn();
			poidsMinColumn.setSortable(true);
			table.addColumn(poidsMinColumn, NLS.constants()
					.regime_field_s_poidsMin());
		}
		if (AccessManager.canReadRegimeDescription()) {
			Column<RegimeProxy, String> poidsMaxColumn = new PoidsMaxColumn();
			poidsMaxColumn.setSortable(true);
			table.addColumn(poidsMaxColumn, NLS.constants()
					.regime_field_s_poidsMax());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(RegimeProxy value) {
		History.newItem(TokenHelper.TK_VIEW + "/regime/" + value.getId(), true);
		return null;
	}

	@Override
	protected String getDefaultSortProperty() {
		return "modified";
	}

	@Override
	protected boolean getDefaultSortPropertyOrder() {
		return false;
	}

	/**
	 * Creates the Create action command for the entity
	 * @return the create command
	 */
	public Command getCreateCommand() {

		if (AccessManager.canCreateRegime() && AccessManager.canEditRegime()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW + "/regime/", true);
				}
			};
			return command;
		} else
			return null;
	}

	/**
	 * Creates the Delete action command for the entity
	 * @return the delete command
	 */
	public PushButton getDeleteButton() {

		if (AccessManager.canDeleteRegime() && AccessManager.canEditRegime()) {
			deleteButton = new PushButton(BaseNLS.constants().button_delete());
			deleteButton.setStyleName(imogResources.imogStyle().imogButton());
			deleteButton.addStyleName("Dynatable-Button");
			deleteButton.setVisible(false);
			return deleteButton;
		}

		return null;
	}

	/**
	 * Creates the Handlers linked to the delete button
	 */
	private void setDeleteButtonHandlers() {

		if (AccessManager.canDeleteRegime() && AccessManager.canEditRegime()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<RegimeProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().regime_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (RegimeProxy entity : selectedEntities) {
							if (count == 1 || i == count - 1)
								msg.append("'"
										+ renderer.getDisplayValue(entity)
										+ "' ?");
							else
								msg.append("'"
										+ renderer.getDisplayValue(entity)
										+ "', ");
							i = i + 1;
						}

						boolean toDelete = Window.confirm(msg.toString());
						if (toDelete) {

							Request<Void> deleteRequest = getRegimeRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected Regime entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListRegimeEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the Regime entries");
									super.onFailure(error);
								}
							});
						}
					}

				}
			}));

			// Selection changed handler	
			registrations.add(requestFactory.getEventBus().addHandler(
					SelectionChangedInTableEvent.TYPE,
					new SelectionChangedInTableEvent.Handler() {
						@Override
						public void noticeSelectionChange(int selectedItems) {
							if (selectedItems > 0)
								deleteButton.setVisible(true);
							else
								deleteButton.setVisible(false);
						}
					}));
		}
	}

	/**
	 * Creates the action command that enables to export the Regime
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportRegime()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.REGIM_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME + "=regime_csv"
							+ "&" + EpicamBirtConstants.REPORT_LOCALE + "="
							+ NLS.constants().locale() + "&"
							+ EpicamBirtConstants.REPORT_FORMAT + "="
							+ EpicamBirtConstants.CSV;

					if (beanDataProvider.getSearchCriterions() != null)
						url = url + getDataProviderCriteria();

					Window.open(url, "_blank", "");
				}
			};
			return command;

		} else
			return null;
	}

	private RegimeRequest getRegimeRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.regimeRequest();
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
		setDeleteButtonHandlers();
		super.onLoad();
	}

	/**
	 * --------------------- * Internal classes * ----------------------
	 */

	/**
	 * Column for field Nom
	 * @author MEDES-IMPS
	 */
	private class NomColumn extends ImogColumn<RegimeProxy, String> {

		public NomColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(RegimeProxy object) {
			String value = null;
			if (object != null) {
				if (object.getNom() == null)
					value = "";
				else
					value = object.getNom();
			}
			return value;
		}

		public String getPropertyName() {
			return "nom";
		}
	}

	/**
	 * Column for field Type
	 * @author MEDES-IMPS
	 */
	private class TypeColumn extends ImogColumn<RegimeProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public TypeColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(RegimeProxy object) {
			String value = null;
			if (object != null) {
				if (object.getType() == null)
					value = "";
				else
					value = renderer.getEnumDisplayValue(RegimeProxy.class,
							"type", object.getType());
			}
			return value;
		}

		public String getPropertyName() {
			return "type";
		}
	}

	/**
	 * Column for field DureeTraitement
	 * @author MEDES-IMPS
	 */
	private class DureeTraitementColumn extends ImogColumn<RegimeProxy, String> {

		public DureeTraitementColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(RegimeProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDureeTraitement() == null)
					value = "";
				else
					value = object.getDureeTraitement().toString();
			}
			return value;
		}

		public String getPropertyName() {
			return "dureeTraitement";
		}
	}

	/**
	 * Column for field PoidsMin
	 * @author MEDES-IMPS
	 */
	private class PoidsMinColumn extends ImogColumn<RegimeProxy, String> {

		public PoidsMinColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(RegimeProxy object) {
			String value = null;
			if (object != null) {
				if (object.getPoidsMin() == null)
					value = "";
				else
					value = object.getPoidsMin().toString();
			}
			return value;
		}

		public String getPropertyName() {
			return "poidsMin";
		}
	}

	/**
	 * Column for field PoidsMax
	 * @author MEDES-IMPS
	 */
	private class PoidsMaxColumn extends ImogColumn<RegimeProxy, String> {

		public PoidsMaxColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(RegimeProxy object) {
			String value = null;
			if (object != null) {
				if (object.getPoidsMax() == null)
					value = "";
				else
					value = object.getPoidsMax().toString();
			}
			return value;
		}

		public String getPropertyName() {
			return "poidsMax";
		}
	}

}
