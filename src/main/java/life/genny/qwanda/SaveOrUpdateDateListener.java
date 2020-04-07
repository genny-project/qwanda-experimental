package life.genny.qwanda;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.hibernate.event.spi.SaveOrUpdateEvent;

public class SaveOrUpdateDateListener extends org.hibernate.event.internal.DefaultSaveOrUpdateEventListener {

	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event) {
		if (event.getObject() instanceof CreatedIntf) {
			CreatedIntf record = (CreatedIntf) event.getObject();
			record.setCreated(LocalDateTime.now(ZoneId.of("Z")));
		}
		super.onSaveOrUpdate(event);
	}
}
