
package com.control_notas.daoimpl;

import com.control_notas.dao.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilterChain {
    
   private final List<Filter> filters = new ArrayList<>();
   private Target target;

   public void addFilter(Filter filter){
      filters.add(filter);
   }

   public boolean actionlogin(String username, String password) throws Exception {
        boolean res = false;
        
        filters.forEach((filter) -> {
            try {
                filter.actionLogin(username, password);
                
            } catch (Exception ex) {
                Logger.getLogger(FilterChain.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        });
        if (target.actionlogin(username, password) > 0) {
            res = true;
        }
        
       return res;
    }

   public void setTarget(Target target){
      this.target = target;
   }
}
