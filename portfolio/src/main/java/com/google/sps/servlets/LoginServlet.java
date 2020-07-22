// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      /*
        Return login status of the user, in JSON form
        if user was logged in: {"email" : "test@example.com", "url" : "logout.url"}
        otherwise: {"email":"", "url":"login.url"}
      */

    response.setContentType("application/json");
    PrintWriter out = response.getWriter();
    UserService userService = UserServiceFactory.getUserService();

    if (userService.isUserLoggedIn()) {
        String email = userService.getCurrentUser().getEmail();
        String logoutUrl = userService.createLogoutURL("/");

        // {"email":"example@example.com","url":"login.com"}
        String json_string = "{\"email\":\"" + email + "\",\"url\":\"" + logoutUrl + "\"}";
        
        out.println(json_string);
    }
    else{
        String loginUrl = userService.createLoginURL("/");
        
        // {"email":"","url":"login.com"}
        String json_string = "{\"email\":\"\",\"url\":\"" + loginUrl + "\"}";

        out.println(json_string);
    }
  }
}