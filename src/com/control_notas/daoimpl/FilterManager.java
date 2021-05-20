
package com.control_notas.daoimpl;

import com.control_notas.dao.Filter;


public class FilterManager {
    FilterChain filterChain;
    
    public FilterManager(Target target)
    {
        filterChain = new FilterChain();
        filterChain.setTarget(target);
    }
    
    public void setFilter(Filter filter)
    {
        filterChain.addFilter(filter);
    }
    

    public boolean filterRequest(String username, String password) throws Exception {
        boolean res = false;
        if (filterChain.actionlogin(username, password)) {
            res = true;
        }
        return res;
    }
}
