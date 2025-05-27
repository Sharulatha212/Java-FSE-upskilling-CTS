const fs = require('fs');
const path = require('path');

// Function to explore directory
function exploreDirectory(dir, indent = '') {
    const items = fs.readdirSync(dir);
    
    items.forEach(item => {
        const fullPath = path.join(dir, item);
        const stats = fs.statSync(fullPath);
        
        if (stats.isDirectory()) {
            console.log(`${indent}📁 ${item}/`);
            exploreDirectory(fullPath, indent + '  ');
        } else {
            const size = (stats.size / 1024).toFixed(2);
            console.log(`${indent}📄 ${item} (${size} KB)`);
        }
    });
}

// Path to Bootstrap directory
const bootstrapPath = path.join(__dirname, 'node_modules', 'bootstrap', 'dist');

console.log('\nExploring Bootstrap Directory Structure:\n');
console.log('Bootstrap dist/ directory:');
exploreDirectory(bootstrapPath);

// Display specific information about key directories
const cssPath = path.join(bootstrapPath, 'css');
const jsPath = path.join(bootstrapPath, 'js');

console.log('\nKey Files in css/ directory:');
fs.readdirSync(cssPath).forEach(file => {
    const stats = fs.statSync(path.join(cssPath, file));
    const size = (stats.size / 1024).toFixed(2);
    console.log(`- ${file} (${size} KB)`);
});

console.log('\nKey Files in js/ directory:');
fs.readdirSync(jsPath).forEach(file => {
    const stats = fs.statSync(path.join(jsPath, file));
    const size = (stats.size / 1024).toFixed(2);
    console.log(`- ${file} (${size} KB)`);
}); 