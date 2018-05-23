package muksihs.steem.postbrowser.client;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;

import gwt.material.design.client.ui.MaterialLoader;
import steem.SteemApi;
import steem.SteemApi.TrendingTagsCallback;
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
		SteemApi.getTrendingTags("", 10, callback);
	}

}
