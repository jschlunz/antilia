package com.antilia.letsplay;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class ToDelete {

	PropertyColumn<Asset> propertyColumn = new PropertyColumn<Asset>(
			new Model<String>("AuditTrain"), "audit_train") {
		
		private static final long serialVersionUID = 1L;

		@Override
		public void populateItem(Item<ICellPopulator<Asset>> item,
				String componentId, IModel<Asset> rowModel) {
			if(rowModel.getObject().getAudit_train() != null)
				super.populateItem(item, componentId, rowModel);
			else {
				item.add(new Label(componentId, "No audit train"));
			}
			item.add(new AttributeModifier("class",true, new Model<String>("Noaudit")));
		}
	};
}
