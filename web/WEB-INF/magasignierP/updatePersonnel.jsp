<%-- 
    Document   : addPersonnel
    Created on : 25 mars 2017, 02:07:02
    Author     : lalanda
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="row-fluid">

    <h2>Change Password For: ${personnel.getNomPrenom()}</h2>

    <div class="widget black">
        <div class="widget-title" style="background-color: #23527c">
            <h4><i class="icon-reorder"></i> Change Password for this User: ${personnel.getNomPrenom()}</h4>

        </div>
        <div class="widget-body ">
            <div class="space15"></div>
            <form class="form-horizontal" method="POST" action="SettingPersonnal?vue=profile&action=updateLoginpersonnel&idPersonne=${personnel.getIdPersonnel()}&niveau=3">
                <div class="control-group">
                    <label class="control-label">Current Password</label>
                    <div class="controls">
                        <input type="password" name="password1" class="span6 ">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">New Password</label>
                    <div class="controls">
                        <input type="password" class="span6 "name="password">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">Re-type New Password</label>
                    <div class="controls">
                        <input type="password" class="span6 " name="password2">
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-success btn-large btn-lg">Change Password</button>
                    <button type="reset"  class="btn btn-large btn-lg btn-danger">Cancel</button>
                </div>

            </form>
        </div>
    </div>
</div>



