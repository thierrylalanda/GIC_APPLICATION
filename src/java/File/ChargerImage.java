/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Administrateur
 */
public class ChargerImage {
    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIRECTORY = "photo";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    private String dossier;
    private HttpServletRequest request;

    public ChargerImage(String dossier, HttpServletRequest request) {
        this.dossier = dossier;
        this.request = request;
    }

    public ChargerImage() {
    }

    
    
    public boolean saveFile(String dossier,HttpServletRequest request){
        
        
      
        return true;
    }
    public String getDossier() {
        return dossier;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setDossier(String dossier) {
        this.dossier = dossier;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    
 
    
    
}
