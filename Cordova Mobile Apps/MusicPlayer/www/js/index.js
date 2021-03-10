/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License. 
 */
var app = {
    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },

    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
		document.getElementById("playAudio").addEventListener("click", playAudio);
		document.getElementById("pauseAudio").addEventListener("click", pauseAudio);
		document.getElementById("stopAudio").addEventListener("click", stopAudio);
		document.getElementById("vol").addEventListener("change", slider);
		document.getElementById("bagpipes").addEventListener("click", bagpipes);
		document.getElementById("parade").addEventListener("click", parade);
		document.getElementById("jig").addEventListener("click", jig);
		document.getElementById("marimba").addEventListener("click", marimba);
		document.getElementById("merengue").addEventListener("click", merengue);
		document.getElementById("man").addEventListener("click", man);
		document.getElementById("never").addEventListener("click", never);
		//document.getElementById("tableAdd").addEventListener("click", createRow);
		
		//This code below was supposed to get me the directory so I could get filenames. It didn't work though.  
		var myPath = cordova.file.externalRootDirectory; // We can use the default externalRootDirectory or use a path : file://my/custom/folder

		window.resolveLocalFileSystemURL(myPath, function (dirEntry) {
			 var directoryReader = dirEntry.createReader();
			 directoryReader.readEntries(onSuccessCallback,onFailCallback);
		});

		function onSuccessCallback(entries){
			alert("success");
		  // The magic will happen here, check out entries with :
		  // console.log(entries);
		}

		function onFailCallback(){
		  // In case of error
		}
		//
		
	},
    // Update DOM on a Received Event
    receivedEvent: function(id) {
        var parentElement = document.getElementById(id);
        var listeningElement = parentElement.querySelector('.listening');
        var receivedElement = parentElement.querySelector('.received');

        listeningElement.setAttribute('style', 'display:none;');
        receivedElement.setAttribute('style', 'display:block;');

        console.log('Received Event: ' + id);
    }
	
	
};


app.initialize();

var myMedia = null;

function playAudio() {
   
   var n = document.getElementById("playing").name; //store text field name in n
   
   var src = "/android_asset/www/music/" +n+ ".mp3";
   //var src = "/Music/Stratovarius Hunting High and Low.mp3";
   //var src = new Audio('bagpipjes.mp3');
   if(myMedia === null) {
      myMedia = new Media(src, onSuccess, onError);
      function onSuccess() {
         console.log("playAudio Success");
      }

      function onError(error) {
         console.log("playAudio Error: " + error.code);
      }
   }
   myMedia.play();
}

function pauseAudio() {
   if(myMedia) {
      myMedia.pause();
   }
}

function stopAudio() {
   if(myMedia) {
      myMedia.stop(); 
   }
   myMedia = null;
}


var volumeValue = 0.5;//0.5;
function slider() {
   if(myMedia && volumeValue > 0 && volumeValue < 1) {
      myMedia.setVolume((document.getElementById("vol").value) * 0.1);//volumeValue += 0.1);
   }
}	


function bagpipes()
{
	document.getElementById("playing").name = "bagpipes"; //change name of text element
	document.getElementById("playing").value = "Bag Pipes"; //change value of text field
	document.getElementById("bagpipes").style.color = "#FFAF00"; //change colors of list items
	document.getElementById("parade").style.color = "#CCCCCC";
	document.getElementById("jig").style.color = "#CCCCCC";
	document.getElementById("marimba").style.color = "#CCCCCC";
	document.getElementById("merengue").style.color = "#CCCCCC";
	document.getElementById("man").style.color = "#CCCCCC";
	document.getElementById("never").style.color = "#CCCCCC";
}

function parade()
{
	document.getElementById("playing").name = "parade"; //change name of text element
	document.getElementById("playing").value = "Parade"; //change value of text field
	document.getElementById("bagpipes").style.color = "#CCCCCC"; //change colors of list items
	document.getElementById("parade").style.color = "#FFAF00";
	document.getElementById("jig").style.color = "#CCCCCC";
	document.getElementById("marimba").style.color = "#CCCCCC";
	document.getElementById("merengue").style.color = "#CCCCCC";
	document.getElementById("man").style.color = "#CCCCCC";
	document.getElementById("never").style.color = "#CCCCCC";
}

function jig()
{
	document.getElementById("playing").name = "jig"; //change name of text element
	document.getElementById("playing").value = "Jig"; //change value of text field
	document.getElementById("bagpipes").style.color = "#CCCCCC"; //change colors of list items
	document.getElementById("parade").style.color = "#CCCCCC";
	document.getElementById("jig").style.color = "#FFAF00";
	document.getElementById("marimba").style.color = "#CCCCCC";
	document.getElementById("merengue").style.color = "#CCCCCC";
	document.getElementById("man").style.color = "#CCCCCC";
	document.getElementById("never").style.color = "#CCCCCC";
}

function marimba()
{
	document.getElementById("playing").name = "marimba"; //change name of text element
	document.getElementById("playing").value = "Marimba"; //change value of text field
	document.getElementById("bagpipes").style.color = "#CCCCCC"; //change colors of list items
	document.getElementById("parade").style.color = "#CCCCCC";
	document.getElementById("jig").style.color = "#CCCCCC";
	document.getElementById("marimba").style.color = "#FFAF00";
	document.getElementById("merengue").style.color = "#CCCCCC";
	document.getElementById("man").style.color = "#CCCCCC";
	document.getElementById("never").style.color = "#CCCCCC";
}

function merengue()
{
	document.getElementById("playing").name = "merengue"; //change name of text element
	document.getElementById("playing").value = "Merengue"; //change value of text field
	document.getElementById("bagpipes").style.color = "#CCCCCC"; //change colors of list items
	document.getElementById("parade").style.color = "#CCCCCC";
	document.getElementById("jig").style.color = "#CCCCCC";
	document.getElementById("marimba").style.color = "#CCCCCC";
	document.getElementById("merengue").style.color = "#FFAF00";
	document.getElementById("man").style.color = "#CCCCCC";
	document.getElementById("never").style.color = "#CCCCCC";
}

function man()
{
	document.getElementById("playing").name = "TheManWhoSoldtheWorld"; //change name of text element
	document.getElementById("playing").value = "The Man Who Sold The World"; //change value of text field
	document.getElementById("bagpipes").style.color = "#CCCCCC"; //change colors of list items
	document.getElementById("parade").style.color = "#CCCCCC";
	document.getElementById("jig").style.color = "#CCCCCC";
	document.getElementById("marimba").style.color = "#CCCCCC";
	document.getElementById("merengue").style.color = "#CCCCCC";
	document.getElementById("man").style.color = "#FFAF00";
	document.getElementById("never").style.color = "#CCCCCC";
}

function never()
{
	document.getElementById("playing").name = "NeverGonnaGiveYouUp"; //change name of text element
	document.getElementById("playing").value = "Never Gonna Give You Up"; //change value of text field
	document.getElementById("bagpipes").style.color = "#CCCCCC"; //change colors of list items
	document.getElementById("parade").style.color = "#CCCCCC";
	document.getElementById("jig").style.color = "#CCCCCC";
	document.getElementById("marimba").style.color = "#CCCCCC";
	document.getElementById("merengue").style.color = "#CCCCCC";
	document.getElementById("man").style.color = "#CCCCCC";
	document.getElementById("never").style.color = "#FFAF00";
}

//This was supposed to dynamically create a table from a directory but couldn't get directories to work
function createRow() {
  var src = "/android_asset/www/music/";
  var table = document.getElementById("table3");
  var row = table.insertRow(1);
  var cell1 = row.insertCell(0);
  cell1.innerHTML = "quack";
	}





	

	

	

	

   

   
 


