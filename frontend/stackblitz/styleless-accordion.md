# Styleless Accordion Component

This is a sample React accordion implementation with minimal styling. It demonstrates component composition and state management.

**Stackblitz Project**: https://stackblitz.com/edit/react-czkjtkao?file=src%2Fstore%2Fstore.js,src%2FApp.js,src%2FAccordion.js,src%2FAccordionItem.js

---

## Project Structure

```
src/
├── store/
│   └── store.js          # Data source for accordion items
├── App.js                # Main app component
├── Accordion.js          # Container component for accordion items
├── AccordionItem.js      # Individual accordion item component
├── index.js              # Entry point
├── style.css             # Global styles
└── package.json
```

---

## Files

### store.js
```javascript
const data = [
  {
    title: 'ABC',
    description: 'djsnd djsnddjsnd\n djsnd djsnddjsnddjsnd djsnddjsnd\n',
  },
  {
    title: 'DEF',
    description: 'nd\n djsnd djsnddjsnddjsnd djsnddjsnd\n',
  },
  {
    title: 'GHI',
    description: 'jk dfnskunfdknjdf kjdfnjkd fjndkjfn',
  },
];

export default data;
```

### App.js
```javascript
import React from 'react';
import './style.css';
import data from './store/store';
import Accordion from './Accordion';

export default function App() {
  return (
    <div>
      <h1>Hello StackBlitz!</h1>
      <p>Start editing to see some magic happen :)</p>
      <Accordion data={data} />
    </div>
  );
}
```

### Accordion.js
```javascript
import React from 'react';
import './style.css';
import AccordionItem from './AccordionItem';

export default function Accordion({ data }) {
  console.log('aacc ::', data);
  return (
    <>
      {data.map((item, index) => (
        <>
          <AccordionItem data={item} />
        </>
      ))}
    </>
  );
}
```

### AccordionItem.js
```javascript
import React, { useState, useMemo } from 'react';
import './style.css';

export default function AccordionItem({ data }) {
  const [show, setShow] = useState(false);
  console.log('child ::', data);
  return (
    <>
      <div style={{ display: 'flex' }}>
        <div id="title"> {data.title} </div>
        <span
          style={{ cursor: 'pointer', padding: 6, background: '#eee' }}
          onClick={() => setShow((show) => !show)}
        >
          &#9733;
        </span>
      </div>
      {show ? <div id="description"> {data.description} </div> : null}
    </>
  );
}
```

---

## Key Points

1. **State Management**: Uses `useState` in `AccordionItem` to toggle visibility
2. **Composition**: `Accordion` maps over data to render multiple `AccordionItem` components
3. **Styling**: Minimal inline styles; no external stylesheet styling
4. **Data Flow**: Data flows from `App` → `Accordion` → `AccordionItem`

---

## Notes

- Uses a star icon (★) as the toggle button
- Each accordion item shows/hides description on click
- No CSS classes used; purely inline styling for the toggle button
