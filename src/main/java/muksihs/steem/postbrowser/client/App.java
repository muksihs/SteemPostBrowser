package muksihs.steem.postbrowser.client;

import java.util.Date;

import com.github.nmorel.gwtjackson.client.ObjectMapper;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;

import gwt.material.design.client.ui.MaterialLoader;
import steem.SteemApi;
import steem.SteemApi.TrendingTagsCallback;
import steem.models.DiscussionsBy;
import steem.models.TrendingTags;
import steem.models.TrendingTags.TrendingTag;

public class App implements ScheduledCommand {

	@Override
	public void execute() {
		MaterialLoader.loading(false);
		TrendingTagsCallback callback = new TrendingTagsCallback() {
			@Override
			public void onResult(String error, TrendingTags result) {
				if (error != null) {
					GWT.log("=== ERROR:");
					GWT.log(error);
				}
				if (result != null) {
					GWT.log("=== RESULT:");
					if (result.getList() == null) {
						GWT.log("--- NULL RESULTS");
						return;
					}
					for (TrendingTag tag : result.getList()) {
						GWT.log(tag.getName() + ": " + tag.getTrending().toString());
					}
				}
			}
		};
		SteemApi.getTrendingTags("dlive-porn", 10, callback);
		DiscussionsBy test = new DiscussionsBy();
		test.setLastUpdate(new Date());
		Mapper mapper = GWT.create(Mapper.class);
		GWT.log("date format test");
		GWT.log(mapper.write(test));
		String jsonText = "{\"parent_author\":null,\"parent_permlink\":null,\"json_metadata\":null,\"last_update\":\"2018-05-22T13:08:54\"}";
		GWT.log(mapper.read(jsonText).getLastUpdate().toString());
	}
	public static interface Mapper extends ObjectMapper<DiscussionsBy>{}

}
