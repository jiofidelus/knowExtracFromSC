package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateDepartPersonnelEvent;
import org.imogene.epicam.client.event.list.ListDepartPersonnelEvent;
import org.imogene.epicam.client.event.view.ViewDepartPersonnelEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.DepartPersonnelFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.DepartPersonnelProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.DepartPersonnelRequest;
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
 * Composite that displays the list of DepartPersonnel entries
 * @author MEDES-IMPS
 */
public class DepartPersonnelDynaTable
		extends
			ImogDynaTable<DepartPersonnelProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public DepartPersonnelDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<DepartPersonnelProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new DepartPersonnelFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadDepartPersonnelDescription()) {
			Column<DepartPersonnelProxy, String> dateDepartColumn = new DateDepartColumn();
			dateDepartColumn.setSortable(true);
			table.addColumn(dateDepartColumn, NLS.constants()
					.departPersonnel_field_s_dateDepart());
		}
		if (AccessManager.canReadDepartPersonnelPersonnel()) {
			Column<DepartPersonnelProxy, String> regionColumn = new RegionColumn();
			regionColumn.setSortable(true);
			table.addColumn(regionColumn, NLS.constants()
					.departPersonnel_field_s_region());
		}
		if (AccessManager.canReadDepartPersonnelPersonnel()) {
			Column<DepartPersonnelProxy, String> districtSanteColumn = new DistrictSanteColumn();
			districtSanteColumn.setSortable(true);
			table.addColumn(districtSanteColumn, NLS.constants()
					.departPersonnel_field_s_districtSante());
		}
		if (AccessManager.canReadDepartPersonnelPersonnel()) {
			Column<DepartPersonnelProxy, String> cDTColumn = new CDTColumn();
			cDTColumn.setSortable(true);
			table.addColumn(cDTColumn, NLS.constants()
					.departPersonnel_field_s_cDT());
		}
		if (AccessManager.canReadDepartPersonnelPersonnel()) {
			Column<DepartPersonnelProxy, String> personnelColumn = new PersonnelColumn();
			personnelColumn.setSortable(true);
			table.addColumn(personnelColumn, NLS.constants()
					.departPersonnel_field_s_personnel());
		}

	}

	@Override
	protected GwtEvent<?> getViewEvent(DepartPersonnelProxy value) {
		History.newItem(
				TokenHelper.TK_VIEW + "/departpersonnel/" + value.getId(), true);
		return null;
	}

	@Override
	protected String getDefaultSortProperty() {
		return "dateDepart";
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

		if (AccessManager.canCreateDepartPersonnel()
				&& AccessManager.canEditDepartPersonnel()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW + "/departpersonnel/",
							true);
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

		if (AccessManager.canDeleteDepartPersonnel()
				&& AccessManager.canEditDepartPersonnel()) {
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

		if (AccessManager.canDeleteDepartPersonnel()
				&& AccessManager.canEditDepartPersonnel()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<DepartPersonnelProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().departPersonnel_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (DepartPersonnelProxy entity : selectedEntities) {
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

							Request<Void> deleteRequest = getDepartPersonnelRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected DepartPersonnel entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListDepartPersonnelEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the DepartPersonnel entries");
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
	 * Creates the action command that enables to export the DepartPersonnel
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportDepartPersonnel()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.DEP_PERS_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME
							+ "=departPersonnel_csv" + "&"
							+ EpicamBirtConstants.REPORT_LOCALE + "="
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

	private DepartPersonnelRequest getDepartPersonnelRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.departPersonnelRequest();
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
	 * Column for field DateDepart
	 * @author MEDES-IMPS
	 */
	private class DateDepartColumn
			extends
				ImogColumn<DepartPersonnelProxy, String> {

		public DateDepartColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(DepartPersonnelProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDateDepart() == null)
					value = "";
				else
					value = DateUtil.getFormatedDate(object.getDateDepart());
			}
			return value;
		}

		public String getPropertyName() {
			return "dateDepart";
		}
	}

	/**
	 * Column for field Region
	 * @author MEDES-IMPS
	 */
	private class RegionColumn extends ImogColumn<DepartPersonnelProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public RegionColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(DepartPersonnelProxy object) {
			String value = null;
			if (object != null) {
				if (object.getRegion() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getRegion());
			}
			return value;
		}

		public String getPropertyName() {
			return "region";
		}
	}

	/**
	 * Column for field DistrictSante
	 * @author MEDES-IMPS
	 */
	private class DistrictSanteColumn
			extends
				ImogColumn<DepartPersonnelProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public DistrictSanteColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(DepartPersonnelProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDistrictSante() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getDistrictSante());
			}
			return value;
		}

		public String getPropertyName() {
			return "districtSante";
		}
	}

	/**
	 * Column for field CDT
	 * @author MEDES-IMPS
	 */
	private class CDTColumn extends ImogColumn<DepartPersonnelProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public CDTColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(DepartPersonnelProxy object) {
			String value = null;
			if (object != null) {
				if (object.getCDT() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getCDT());
			}
			return value;
		}

		public String getPropertyName() {
			return "CDT";
		}
	}

	/**
	 * Column for field Personnel
	 * @author MEDES-IMPS
	 */
	private class PersonnelColumn
			extends
				ImogColumn<DepartPersonnelProxy, String> {

		private EpicamRenderer renderer = EpicamRenderer.get();

		public PersonnelColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(DepartPersonnelProxy object) {
			String value = null;
			if (object != null) {
				if (object.getPersonnel() == null)
					value = "";
				else
					value = renderer.getDisplayValue(object.getPersonnel());
			}
			return value;
		}

		public String getPropertyName() {
			return "personnel";
		}
	}

}
