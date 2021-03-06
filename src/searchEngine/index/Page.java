package searchEngine.index;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represent page of Wikipedia 
 * with just title, content and list of entities
 * @author amaury Lavieille - antoine Cellier
 *
 */
public class Page {
	
	/**
	 * Page's title
	 */
	private String title;
	
	/**
	 * Page's content
	 */
	private String content;
	
	
	/**
	 * Page's id
	 */
	private String id; 
	
	
	/**
	 * List of content's entities
	 */
	private ArrayList<String> entities;
	
	/**
	 * Builder
	 * Initialize page with empty title and content
	 */
	public Page()
	{
		this.title = "";
		this.content = "";
		this.entities = new ArrayList<String>();
	}
	
	/**
	 * Builder. Initialize page with title, content and list of entity
	 * @param String title Page's title
	 * @param String content Page's content
	 * @param ArrayList<String> list of entity
	 */
	public Page(String title, String content, String id, ArrayList<String> entities){
		this.title = title;
		this.content = content;
		this.id = id;
		this.entities = entities;
	}
	
	
	/**
	 * Clone an instance of page
	 */
	public Page clone()
	{
		return new Page(this.title,this.content,this.id,this.entities);
	}
	
	/**
	 * Extract content's entities and added to the list
	 */
	public void extractEntities(){
		Pattern p = Pattern.compile("\\[\\[([^\\]]+)\\]\\]");
		Matcher m = p.matcher(this.content);
		
		while(m.find()) {
			String[] entitesGroup = m.group(1).split("\\|");
			if(entitesGroup.length > 0)
				entities.add(entitesGroup[0]);	    
		}
	}
	
	/**
	 * Join the list of entities
	 * @return String
	 */
	public String joinEntities(){
		String res = "";
		for (String entity : this.entities) {
			res += entity+" ; ";
		}
		return res;
	}
	
	/**
	 * Add text to existing content
	 * @param String content
	 */
	public void concatContent(String content){
		this.content += content;
	}
	
	/**
	 * Get title
	 * @return String
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set title
	 * @param String title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Get content
	 * @return String
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * Set content
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * Get entities
	 * @return
	 */
	public ArrayList<String> getEntities(){
		return this.entities;
	}
	
	/**
	 * Add entity
	 * @param String entity
	 */
	public void addEntity(String entity){
		//System.out.println(entity);
		this.entities.add(entity);
	}
	
	/**
	 * Get id
	 * @return String
	 */
	public String getId(){
		return this.id;
	}
	
	/**
	 * Set id
	 * @return String
	 */
	public void setId(String id){
		this.id = id;
	}
	
	/**
	 * Convert page to xml
	 * @param Document document 
	 * @return Element
	 */
	public String toXml(){
		
		String resString = "<page>\n";
		resString += "\t<title>"+this.title+"</title>\n";
		resString += "\t<id>"+this.id+"</id>\n";
		resString += "\t<entities>\n";
		if(this.entities.size() > 0) {
			for (String entity : entities) {
				resString += "\t\t<entity>"+entity+"</entity>\n";
			}
		}
		resString += "\t</entities>\n";
		resString += "</page>\n";
		return resString;
	}
}
