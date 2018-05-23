package steem.models;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscussionsBy {
	private BigInteger id;
	private String author;
	private String permlink;
	private String category;
	@JsonProperty("parent_author")
	private String parentAuthor;
	@JsonProperty("parent_permlink")
	private String parentPermlink;
	private String title;
	private String body;
	@JsonProperty("json_metadata")
	private String jsonMetadata;
	@JsonProperty("last_update")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone="UTC")
	private Date lastUpdate;
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
