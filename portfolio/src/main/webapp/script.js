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


/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

function getComments(){
    fetch('/data').then(response => response.json()).then((comment_history) => {
        console.log(comment_history);

        const commentsTable = document.getElementById('comments_table');

        // Header
        var new_th_item = commentsTable.insertRow();
            
        var user_td_item = new_th_item.insertCell();
        user_td_item.innerHTML ="User";
        var comment_td_item = new_th_item.insertCell();
        comment_td_item.innerHTML = "Comment";

        // Each comments
        comment_history.forEach((each_comment) => {
            var new_tr_item = commentsTable.insertRow();
            
            var user_td_item = new_tr_item.insertCell();
            user_td_item.innerHTML = each_comment.email;
            
            var comment_td_item = new_tr_item.insertCell();
            comment_td_item.innerHTML = each_comment.comment;
        })
    });
}

function varifyLoginStatus(){
    fetch('/login').then(response => response.json()).then((text) =>{
        var button = document.getElementById('log_button');
        button.onclick = function() {
            window.location.href = text.url;
        }

        if(text.email == ""){
            // Not login yet
        }
        else{
            document.getElementById("email").value = text.email;

            // Already login, changed button's text
            var button = document.getElementById('log_button');
            button.innerHTML = "Log out";

            document.getElementById('form').style.display = "block";
            document.getElementById('see_comment').style.display = "block";
        }
    });
}