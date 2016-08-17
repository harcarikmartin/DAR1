<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <div class="row col-lg-12">
			<div
				class="col-lg-offset-1 col-lg-10 col-md-12 col-sm-12 col-xs-12 rowBackground">
				<table
					class="col-lg-offset-4 col-lg-4 col-md-offset-3 col-md-6 col-sm-offset-3 col-sm-6 col-xs-offset-2 col-xs-8">
					<tr>
						<td class="userProfileInfo text-right paddingHorizontal">Username:</td>
						<td class="userProfileInfo paddingHorizontal">${user.userName}</td>
					</tr>
					<tr>
						<td class="userProfileInfo text-right paddingHorizontal">Birthdate:</td>
						<td class="userProfileInfo paddingHorizontal">${user.birthDate}</td>
					</tr>
					<tr>
						<td class="userProfileInfo text-right paddingHorizontal">Role:</td>
						<td class="userProfileInfo paddingHorizontal">${user.role}</td>
					</tr>
					<tr>
						<td class="userProfileInfo text-right paddingHorizontal">Status:</td>
						<td class="userProfileInfo paddingHorizontal">${user.status}</td>
					</tr>
				</table>

			</div>
		</div>