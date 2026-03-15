# HTML5 Recap

Here is a brief Markdown recap of what we covered in HTML5 so far.

## Semantic HTML5

- Use meaningful tags instead of just `<div>`:
  - Layout/structure: `<header>`, `<nav>`, `<main>`, `<section>`, `<article>`, `<aside>`, `<footer>`.
  - Meaning: code becomes self-documenting and easier to maintain.
- Benefits:
  - Better accessibility (screen readers understand page regions).
  - Better SEO (search engines understand structure and importance).
  - Cleaner, more readable HTML.

## ARIA (Accessible Rich Internet Applications)

- Purpose: add extra info so assistive tech understands custom UI.
- Key pieces:
  - Roles: `role="button"`, `role="dialog"`, `role="navigation"`, etc.
  - States: `aria-expanded`, `aria-checked`, `aria-disabled`, `aria-pressed`.
  - Labels: `aria-label`, `aria-labelledby`, `aria-describedby`.
  - Live regions: `aria-live` for dynamic announcements.
- Rules of thumb:
  - Prefer native elements first (`<button>`, `<a>`, `<input>`).
  - Use ARIA mainly when HTML alone cannot express the widget.

## Keyboard Accessibility and Focus

- Elements that are naturally focusable:
  - `<a href>`, `<button>`, `<input>`, `<select>`, `<textarea>`, etc.
- `tabindex`:
  - `tabindex="0"`: adds element to normal tab order.
  - `tabindex="-1"`: focusable only via JS (`element.focus()`), not Tab.
  - Avoid positive values (`tabindex="1"`, `2`, ...).
- Good practices:
  - All interactive UI must be usable with keyboard only (Tab, Shift+Tab, Enter, Space, Arrow keys, Esc).
  - Keep focus order logical (matches visual order).
  - Always show a visible focus style (do not remove outline without replacement).
  - For modals: move focus into modal when opened, trap focus inside, restore to trigger when closed.

## Accessibility Basics (Beyond ARIA)

- Use proper HTML:
  - Correct elements for their purpose (link vs button vs input).
  - Semantic structure with headings (`<h1>` to `<h6>`) and landmarks.
- Text alternatives:
  - Meaningful images: `alt="..."`.
  - Decorative images: `alt=""`.
- Visual design:
  - Sufficient color contrast.
  - Do not rely only on color for meaning (add icons/text).
- Content:
  - Clear, descriptive labels and button text.
  - Predictable behaviors (no surprise focus jumps or auto-playing content).

## Meta Tags

- Placed inside `<head>`, not visible in UI, but affect behavior/SEO/sharing.
- Common meta tags:
  - `<meta charset="UTF-8">`: character encoding.
  - `<meta name="viewport" content="width=device-width, initial-scale=1">`: responsive behavior on mobile.
  - `<meta name="description" content="Short page summary">`: snippet often shown in search results.
  - Open Graph / Twitter meta (`og:title`, `og:description`, `og:image`): how page looks when shared.
- Goal: describe the page to browsers, search engines, and social platforms.

## Next Step

Build a tiny "perfect" sample page combining semantic layout, solid meta tags, basic ARIA, and proper keyboard focus handling.
