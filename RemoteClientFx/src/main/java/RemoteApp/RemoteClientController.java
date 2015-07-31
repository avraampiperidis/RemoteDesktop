package RemoteApp;

import RemoteApp.JNI.KillProcCplusplus;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import RemoteApp.model.ListViewModel;
import RemoteApp.model.serializables.MultiCast;
import RemoteApp.network.MultiCastStatus;
import RemoteApp.network.NetworkInter;

public class RemoteClientController implements Initializable {

    @FXML
    private ListView<ListViewModel> listview;
    
    @FXML
    private ComboBox combobox;
    
    private List<ListViewModel> list;
        
    public static String ip;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    listview.setStyle("/css/listview.css");
            ThreadContainer.setMulticast(new MultiCastStatus());
            
                ArrayList<String> ips = new ArrayList<>();
                try {
                    ips = NetworkInter.getAllIp();                                     
                    combobox.getItems().addAll(ips);
                    combobox.getSelectionModel().select(0);                  
                } catch (SocketException ex) {
                    Logger.getLogger(RemoteClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                    
                  // updateUithread = 
                   ThreadContainer.setUithread(new Thread() {
                        @Override
                        public void run() {
                            try {
                            while(!Thread.currentThread().isInterrupted()) {
                        
                            Thread.sleep(3500);
                            ip = combobox.getSelectionModel().getSelectedItem().toString();
                            list = new ArrayList<>();
                            
                            Iterator<Entry<String,MultiCast>> it = Constants.getNetworkmap().entrySet().iterator();
                                while(it.hasNext()) {
                                    Entry<String,MultiCast> pair = it.next();
                                        list.add(new ListViewModel(pair.getValue().getHostName(),pair.getValue().getIp(),pair.getValue().getPort(),pair.getValue().isActive(),pair.getValue().isPasswordRequired()));
                                }
                            
                            Constants.resetHashMap();                            
                            final ObservableList<ListViewModel> obs = FXCollections.observableArrayList(list);
                            
                            
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                            listview.setItems(obs);   
                            listview.setCellFactory(new Callback<ListView<ListViewModel>,ListCell<ListViewModel>>() {

                            @Override
                            public ListCell<ListViewModel> call(ListView<ListViewModel> p) {
                            ListCell<ListViewModel> cell = new ListCell<ListViewModel>() {
                            
                            @Override
                            protected void updateItem(ListViewModel t,boolean bln) {
                                super.updateItem(t, bln);
                                if(t != null) {
                                    setText(t.getHostname()+ "\t"+t.getIp()+"\t port:"+t.getPort()+"\t password:"+t.password());                                  
                                    setGraphic(t.getButton());
                                    setPadding(new Insets(15,10,15,10));                                             
                                }
                            }
                            };
                        
                            return cell;
                            }
                    
                            });
                                
                            }
                        });
                            
                                           
                    }
                            } catch (InterruptedException ex) {
                            Logger.getLogger(RemoteClientController.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                        }
                    });
                    
	}
        
        

}
