# Accessibility & SEO Cheat Sheet (SPA + SSR)

## 1. Making UI accessible for disabilities

- Use **semantic HTML** so screen readers understand structure: headings (`h1–h6`), lists, buttons, links, form labels, and landmarks (`<header>`, `<nav>`, `<main>`, `<footer>`).  
- Add **ARIA** only when semantics aren’t enough (e.g. `role="dialog"`, `aria-expanded`, `aria-live`), and keep it in sync with actual UI state.  
- Ensure full **keyboard support**: all interactive elements reachable via Tab, visible focus outlines, and logical focus order.  
- Manage **focus** on UI changes: move focus into dialogs/sidebars when they open, and back to the triggering element when they close.

---

## 2. Accessibility: structure & semantics

- Use proper **document outline**: a single `h1` per page/route, nested headings in order, and meaningful text in headings and buttons.  
- Prefer real elements over divs: `<button>` for actions, `<a href="...">` for navigation, `<form>`, `<label>`, `<input>` for forms.  
- Use landmarks to group regions (`<header>`, `<nav>`, `<main>`, `<aside>`, `<footer>`) so assistive tech can jump quickly.  
- Avoid using only color for meaning; pair it with text or icons for status, errors, success, etc.

---

## 3. Accessibility: ARIA & dynamic content

- Use ARIA roles for custom widgets (e.g. `role="dialog"` for modals, `role="alert"` or `aria-live` for toasts and errors).  
- Tie controls to content with attributes like `aria-labelledby`, `aria-describedby`, and `aria-controls`.  
- When content updates dynamically (search results, filters), announce changes with `aria-live` regions instead of silently changing the DOM.  
- Keep ARIA state (`aria-expanded`, `aria-pressed`, `aria-selected`) updated whenever UI state changes.

---

## 4. Accessibility: keyboard, focus & forms

- Ensure every interactive element is reachable and operable with keyboard only (Tab, Shift+Tab, Enter/Space).  
- Never remove focus outlines without providing a visible replacement; users must see where focus is.  
- Trap focus inside modals while they’re open and restore it to the trigger on close.  
- For forms, always connect `<label>` and `<input>` (using `for`/`id`), provide clear error messages, and link errors to fields with `aria-describedby`.

---

## 5. SEO & crawlability: rendering strategy

- For important routes, use **SSR/SSG** so HTML content is present on first response, making it easy for crawlers and assistive tech.  
- Hydrate on the client to add interactivity, but keep the initial server HTML meaningful and close to the final UI to avoid confusion.  
- Use clean, descriptive URLs that reflect page content (e.g. `/hotels/bangkok` instead of `/page?id=123`).  
- Make sure navigation uses real links (`<a href>`) so crawlers and users can reach all key pages.

---

## 6. SEO: metadata & content

- Set per-route `<title>` and `<meta name="description">` to describe the page accurately and improve search snippets.  
- Use canonical tags (`<link rel="canonical" href="...">`) to avoid duplicate content issues.  
- Add Open Graph and Twitter meta tags for better link previews on social platforms.  
- Provide structured content: meaningful headings, alt text for images, and text equivalents for icons and important visuals.

---

## 7. SEO & a11y: performance & UX

- Optimize performance (bundle splitting, critical CSS, image optimization) so key content loads quickly, which helps both SEO and users on slow devices.  
- Avoid large layout shifts during or after hydration to keep reading position and focus stable.  
- Lazy-load below-the-fold content but keep above-the-fold content directly in the initial HTML where possible.  
- Handle error/empty states with clear messages and proper semantics so both users and crawlers understand what’s happening.
