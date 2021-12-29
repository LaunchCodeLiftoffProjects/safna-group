document.querySelectorAll(".drop-zone__input").forEach((inputElement) => {
  const dropZoneElement = inputElement.closest(".drop-zone");

  dropZoneElement.addEventListener("click", (e) => {
    inputElement.click();
  });

  inputElement.addEventListener("change", (e) => {
    if (inputElement.files.length) {
      updateThumbnail(dropZoneElement, inputElement.files[0]);
    }
  });

  dropZoneElement.addEventListener("dragover", (e) => {
    e.preventDefault();
    dropZoneElement.classList.add("drop-zone--over");
  });

  ["dragleave", "dragend"].forEach((type) => {
    dropZoneElement.addEventListener(type, (e) => {
      dropZoneElement.classList.remove("drop-zone--over");
    });
  });

  dropZoneElement.addEventListener("drop", (e) => {
    e.preventDefault();

    if (e.dataTransfer.files.length) {
      inputElement.files = e.dataTransfer.files;
      updateThumbnail(dropZoneElement, e.dataTransfer.files[0]);
    }

    dropZoneElement.classList.remove("drop-zone--over");
  });
});

/**
 * Updates the thumbnail on a drop zone element.
 *
 * @param {HTMLElement} dropZoneElement
 * @param {File} file
 */
function updateThumbnail(dropZoneElement, file) {
  let thumbnailElement = dropZoneElement.querySelector(".drop-zone__thumb");

  // First time - remove the prompt
  if (dropZoneElement.querySelector(".drop-zone__prompt")) {
    dropZoneElement.querySelector(".drop-zone__prompt").remove();
  }

  // First time - there is no thumbnail element, so lets create it
  if (!thumbnailElement) {
    thumbnailElement = document.createElement("div");
    thumbnailElement.classList.add("drop-zone__thumb");
    dropZoneElement.appendChild(thumbnailElement);
  }

  thumbnailElement.dataset.label = file.name;

  // Show thumbnail for image files
  if (file.type.startsWith("image/")) {
    const reader = new FileReader();

    reader.readAsDataURL(file);
    reader.onload = () => {
      thumbnailElement.style.backgroundImage = `url('${reader.result}')`;
    };
  } else {
    thumbnailElement.style.backgroundImage = null;
  }
}




////selecting all required elements
//const dropArea = document.querySelector(".drag-area"),
//dragText = dropArea.querySelector("header"),
//button = dropArea.querySelector("button"),
//input = dropArea.querySelector("input");
//let file; //this is a global variable and we'll use it inside multiple functions
//
//button.onclick = ()=>{
//  input.click(); //if user click on the button then the input also clicked
//}
//
//input.addEventListener("change", function(){
//  //getting user select file and [0] this means if user select multiple files then we'll select only the first one
//  file = this.files[0];
//  dropArea.classList.add("active");
//  showFile(); //calling function
//});
//
//
////If user Drag File Over DropArea
//dropArea.addEventListener("dragover", (event)=>{
//  event.preventDefault(); //preventing from default behaviour
//  dropArea.classList.add("active");
//  dragText.textContent = "Release to Upload File";
//});
//
////If user leave dragged File from DropArea
//dropArea.addEventListener("dragleave", ()=>{
//  dropArea.classList.remove("active");
//  dragText.textContent = "Drag & Drop to Upload File";
//});
//
////If user drop File on DropArea
//dropArea.addEventListener("drop", (event)=>{
//  event.preventDefault(); //preventing from default behaviour
//  //getting user select file and [0] this means if user select multiple files then we'll select only the first one
//  file = event.dataTransfer.files[0];
//  showFile(); //calling function
//});
//
//function showFile(){
//  let fileType = file.type; //getting selected file type
//  let validExtensions = ["image/jpeg", "image/jpg", "image/png"]; //adding some valid image extensions in array
//  if(validExtensions.includes(fileType)){ //if user selected file is an image file
//    let fileReader = new FileReader(); //creating new FileReader object
//    fileReader.onload = ()=>{
//      let fileURL = fileReader.result; //passing user file source in fileURL variable
//        // UNCOMMENT THIS BELOW LINE. I GOT AN ERROR WHILE UPLOADING THIS POST SO I COMMENTED IT
//      // let imgTag = `<img src="${fileURL}" alt="image">`; //creating an img tag and passing user selected file source inside src attribute
//      dropArea.innerHTML = imgTag; //adding that created img tag inside dropArea container
//    }
//    fileReader.readAsDataURL(file);
//  }else{
//    alert("This is not an Image File!");
//    dropArea.classList.remove("active");
//    dragText.textContent = "Drag & Drop to Upload File";
//  }
//}