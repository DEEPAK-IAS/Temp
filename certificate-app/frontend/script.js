function generateCertificate() {
  const name = document.getElementById("nameInput").value;
  if (!name) {
      alert("Please enter a name!");
      return;
  }

  const canvas = document.getElementById("certificateCanvas");
  const ctx = canvas.getContext("2d");

  const img = new Image();
  img.src = "certificate_template.jpg"; 
  img.onload = function () {
      canvas.width = img.width;
      canvas.height = img.height;

      ctx.drawImage(img, 0, 0, img.width, img.height);

      ctx.font = "40px Arial";
      ctx.fillStyle = "black";
      ctx.textAlign = "center";

      ctx.fillText(name, img.width / 2, img.height / 2);

      const downloadBtn = document.getElementById("downloadBtn");
      downloadBtn.href = canvas.toDataURL("image/png");
      downloadBtn.download = "certificate.png";
      downloadBtn.style.display = "block";
      downloadBtn.innerText = "Download Certificate";

      document.getElementById("saveBtn").style.display = "block";
  };
}

function saveToServer() {
  const canvas = document.getElementById("certificateCanvas");
  const imageData = canvas.toDataURL("image/png");

  fetch("http://localhost:3000/save-certificate", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ imageData }),
  })
  .then(response => response.text())
  .then(data => alert(data))
  .catch(error => console.error("Error:", error));
}
