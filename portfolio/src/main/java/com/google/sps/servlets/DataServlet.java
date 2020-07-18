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
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
    private ArrayList<String> quote_list_;

    @Override
    public void init() {
        quote_list_ = new ArrayList<>();
        quote_list_.add("Hello this is Hsin-Mei Lin");
        quote_list_.add("I'm from Taiwan");
        quote_list_.add("Welecome here!");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Send the JSON as the response
        response.setContentType("application/json;");
        Gson gson = new Gson();
        response.getWriter().println(gson.toJson(quote_list_));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String input = request.getParameter("text-input");
        quote_list_.add(input);

        // Redirect back to the HTML page.
        response.sendRedirect("/index.html");
    }
}