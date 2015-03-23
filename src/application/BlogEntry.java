package application;
import javax.servlet.http.HttpSession;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "blog_entry")
public class BlogEntry  extends BaseDaoEnabled{
	public static final String ID_FIELD = "id";

	public static final String TITLE_FIELD = "title";

	public static final String CONTENT_FIELD = "content";

	public static final String DATE_FIELD = "date";

	private void init() {
		try {
			ConnectionSource connectionSource = ConnectionPool.getConnection();
			Dao<BlogEntry, Integer> blogEntryDao = DaoManager.createDao(
					connectionSource, BlogEntry.class);
			this.setDao(blogEntryDao);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public BlogEntry() {
		this.init();
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@DatabaseField(generatedId = true,columnName = ID_FIELD)
	private long id;

	@DatabaseField(canBeNull = false,columnName = TITLE_FIELD)
	private String title;

	@DatabaseField(canBeNull = false,dataType = DataType.LONG_STRING,columnName = CONTENT_FIELD)
//	@DatabaseField(dataType = DataType.LONG_STRING)
	private String content;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@DatabaseField(canBeNull = false,columnName = DATE_FIELD)
	private String date;
	
	
	public void setId(long id) {
		this.id = id;	
	}
}
