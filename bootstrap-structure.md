# Bootstrap Directory Structure

The Bootstrap framework files are located in `node_modules/bootstrap/dist/`. Here's a detailed explanation of each directory and its contents:

## CSS Directory (`dist/css/`)

Contains the compiled CSS files for styling your web pages:

- `bootstrap.css` - The unminified CSS file with full formatting and comments
- `bootstrap.min.css` - The minified version for production use
- `bootstrap.css.map` - Source map file for debugging
- `bootstrap.rtl.css` - Right-to-left support version
- `bootstrap.rtl.min.css` - Minified RTL version
- `bootstrap.rtl.css.map` - Source map for RTL version

## JavaScript Directory (`dist/js/`)

Contains the compiled JavaScript files for Bootstrap's interactive components:

- `bootstrap.bundle.js` - Unminified JS file including Popper.js
- `bootstrap.bundle.min.js` - Minified version with Popper.js
- `bootstrap.js` - Core Bootstrap JavaScript without Popper.js
- `bootstrap.min.js` - Minified core JavaScript
- `bootstrap.bundle.js.map` - Source map for the bundle
- `bootstrap.js.map` - Source map for core JS

## SCSS Directory (`scss/`)

Contains the source Sass files:

- `_variables.scss` - Customizable variables
- `_mixins/` - Reusable Sass mixins
- `_functions/` - Sass functions
- Components folders for individual Bootstrap components

## Key Files and Their Uses:

1. **CSS Files:**

   - Use `bootstrap.min.css` for production
   - Use `bootstrap.css` for development and debugging
   - RTL files for right-to-left language support

2. **JavaScript Files:**

   - Use `bootstrap.bundle.min.js` for production (includes Popper.js)
   - Use `bootstrap.bundle.js` for development
   - Individual component files available for custom builds

3. **Source Maps:**
   - `.map` files help with debugging by mapping the minified code back to source
   - Useful during development for inspecting styles

## Current Project Structure:

```
bootstrap/
├── node_modules/
│   └── bootstrap/
│       └── dist/
│           ├── css/
│           └── js/
├── local.html
├── package.json
└── bootstrap-structure.md
```

## Usage in Our Project:

We're currently using:

- CSS: `node_modules/bootstrap/dist/css/bootstrap.min.css`
- JS: `node_modules/bootstrap/dist/js/bootstrap.bundle.min.js`

These files provide all necessary styles and functionality for Bootstrap components.
