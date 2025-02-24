const express = require("express");
const fs = require("fs");
const cors = require("cors");

const app = express();
app.use(cors());
app.use(express.json({ limit: "10mb" }));

app.post("/save-certificate", (req, res) => {
    const { imageData } = req.body;

    if (!imageData) {
        return res.status(400).send("No image data received.");
    }

    const base64Data = imageData.replace(/^data:image\/png;base64,/, "");
    
    fs.writeFile("certificate.jpg", base64Data, "base64", (err) => {
        if (err) {
            console.error("Error saving image:", err);
            return res.status(500).send("Error saving certificate.");
        }
        res.send("Certificate saved successfully!");
    });
});

app.listen(3000, () => console.log("Server running on http://localhost:3000"));
