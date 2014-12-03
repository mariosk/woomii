/**
* <h1>WooMii Platform</h1>
* CronWorker: CronWorker that will compact the database periodically from unused records.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.utils;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WooMiiCronWorker implements Worker {

	protected static Logger logger = Logger.getLogger("worker");
	protected static ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/applicationContext.xml");
	protected static BasicDataSource bsProduction = (BasicDataSource) context.getBean("dataSourceProduction");
	protected static BasicDataSource bsSandbox = (BasicDataSource) context.getBean("dataSourceSandbox");

	public void work() {		
		logger.info("Thread has began working.");		
		/*
		int entriesToKeep = 0;
		List<DataStreams> dataStreamsToDelete = new ArrayList<DataStreams>();
        try {        	        
        	List<Sites> sites = Sites.findAllSites();
        	Iterator<Sites> s = sites.iterator();
	        while(s.hasNext()){
	        	dataStreamsToDelete.clear();
	        	Sites site = s.next();
	        	entriesToKeep = site.getTotal_DataStreams();
	        	logger.info("[" + site.getName() + "]: Configuration (Total DataStreams to keep) for site: " + site.getName() + " = " + entriesToKeep);
	        	List<Assets> assets = Assets.findAssetBySiteId(site.getId());
	        	Iterator<Assets> a = assets.iterator();
	        	while(a.hasNext()){
	        		Assets asset = a.next();
	        		List<Channels> channels = Channels.findChannelByAssetId(asset.getId());
	        		Iterator<Channels> c = channels.iterator();
	        		while(c.hasNext()){
	        			Channels channel = c.next();	        			
	        			List<DataStreams> dataStreams = DataStreams.findDataStreamByChannelId(channel.getId());
	        			Iterator<DataStreams> d = dataStreams.iterator();
	    	        	while(d.hasNext()){
	    	        		DataStreams dataStream = d.next();
	    	        		dataStreamsToDelete.add(dataStream);
	    	        	}	        			
	        		}	        			        	
	        	}
	        	logger.info("[" + site.getName() + "]: Found " + dataStreamsToDelete.size() + " dataStreams in this site (" + site.getName() + ")");
	        	logger.info("[" + site.getName() + "]: Sorting datastreams by ServerDT...");
	        	Collections.sort(dataStreamsToDelete, new DataStreamsComparator());
	        	
	        	int rowsAffected = 0;
	        	if (dataStreamsToDelete.size() > entriesToKeep) {
	        		DataStreams firstDataStream = dataStreamsToDelete.get(dataStreamsToDelete.size()-1); // this entry is the oldest one.
	        		logger.info("[" + site.getName() + "]: First entry (serverdt) = " + firstDataStream.getServerDT() + " to delete (id) = " + firstDataStream.getId());	        		
	        		DataStreams lastDataStream = dataStreamsToDelete.get(entriesToKeep); // this entry is the newer one up to which we must delete.
	        		logger.info("[" + site.getName() + "]: Last entry (serverdt) = " + lastDataStream.getServerDT() + " to delete (id) = " + lastDataStream.getId());
	        		    				
    				Connection con = null;
    				PreparedStatement st = null;
                    try {
                        Class.forName(bs.getDriverClassName());
                        con = DriverManager.getConnection(bs.getUrl(), bs.getUsername(), bs.getPassword());
                        try {
                            // Delete record
                            String sql = "DELETE FROM datastreams WHERE Id >='" + firstDataStream.getId() + "' AND Id <= '" + lastDataStream.getId() + "'";
                            st = con.prepareStatement(sql);
                            rowsAffected = st.executeUpdate();                            
                        } catch (SQLException sException) {
                        	Thread.currentThread().interrupt();
                        	logger.error("[" + site.getName() + "]: " + sException.getMessage());
                        	sException.printStackTrace();
                        }
                        con.close();
                    } catch (Exception e) {
                    	Thread.currentThread().interrupt();
                    	logger.error("[" + site.getName() + "]: " + e.getMessage());
                        e.printStackTrace();
                    }	        			        		        		
	        	}
	        	logger.info("[" + site.getName() + "]: Rows affected by deleteDataStreamEntries = " + rowsAffected);
	        }        	            
        }
        catch (Exception e) {
            Thread.currentThread().interrupt();
            logger.error(e.getMessage());
            e.printStackTrace();
        }                              
        */
        logger.info("Thread has completed work.");
        //dataStreamsToDelete.clear();        
	}
	
}

/*
class DataStreamsComparator implements Comparator<DataStreams> {
    @Override
    public int compare(DataStreams d1, DataStreams d2) {
    	// return result in descending order
        return d2.getServerDT().compareTo(d1.getServerDT());
    }
}
*/