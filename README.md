# StyleTextUtils – Advanced Text Styling Extension for Kodular / App Inventor

`StyleTextUtils` is an Android extension for **Kodular** and other **App Inventor–based** platforms that lets you apply rich text styling to any `Label` or `TextBox` component.

It wraps Android’s `SpannableString` APIs into easy-to-use blocks so you can:

- Blur parts of the text  
- Make segments of text clickable and handle click events  
- Apply custom fonts stored in the app assets  
- Change font size only for selected ranges  
- Configure line height for the whole text  
- Add bullet points to specific lines  
- Remove all styling and reset the text  
- Find start / end positions of substrings to style them programmatically  

## Blocks / Functions

This extension adds advanced text styling utilities for `Label` and `TextBox` components.

> **Indexing note**  
> All positions are **1-based**:  
> - `startIndex` is inclusive  
> - `endIndex` is exclusive  
> If the range is invalid, the block throws a clear `YailRuntimeError`.

---

### BlurText

**Signature:** `BlurText(component, startIndex, endIndex, blurRadius)`

Blurs a selected portion of the text.

- `component` – Target `Label` / `TextBox`
- `startIndex` – 1-based start of the range (inclusive)
- `endIndex` – 1-based end of the range (exclusive)
- `blurRadius` – Blur radius in pixels (higher = stronger blur)

---

### GetStartPos

**Signature:** `GetStartPos(component, textToFind) → string`

Finds all occurrences of a substring and returns their **start positions** (1-based) as a comma-separated string.

- `component` – `Label` / `TextBox`
- `textToFind` – Substring to search for  
- **Returns:** e.g. `"1,15,30"` if the substring appears at those positions

---

### GetEndPos

**Signature:** `GetEndPos(component, textToFind) → string`

Finds all occurrences of a substring and returns their **end positions** (1-based) as a comma-separated string.

- `component` – `Label` / `TextBox`
- `textToFind` – Substring to search for  
- **Returns:** positions of the character *after* each match (useful with the other blocks)

---

### RemoveStyling

**Signature:** `RemoveStyling(component)`

Removes all spans / styling applied via this extension by re-parsing the current text as HTML.

- `component` – `Label` / `TextBox`  
- Use this to “reset” the text back to plain formatting.

---

### SetClickable

**Signature:** `SetClickable(component, startIndex, endIndex, color, underline)`

Makes a portion of the text clickable and fires an event when tapped.

- `component` – Target `Label` / `TextBox`
- `startIndex` – 1-based start (inclusive)
- `endIndex` – 1-based end (exclusive)
- `color` – Integer ARGB color for the clickable text
- `underline` – `true` to show underline, `false` to hide it

Internally this uses a custom `ClickableSpan` and enables `LinkMovementMethod` on the `TextView`.

#### Event: TextClicked

**Signature:** `TextClicked(component, clickedText)`

Raised when the user taps on a clickable segment created with `SetClickable`.

- `component` – The component whose text was clicked
- `clickedText` – The exact substring between `startIndex` and `endIndex - 1`

---

### SetCustomTypeface

**Signature:** `SetCustomTypeface(component, startIndex, endIndex, fontAssetPath, fontSizeSp)`

Applies a custom font and size to a specific text range.

- `component` – `Label` / `TextBox`
- `startIndex` – 1-based start (inclusive)
- `endIndex` – 1-based end (exclusive)
- `fontAssetPath` – Path inside the app’s assets folder (e.g. `fonts/MyFont.ttf`)
- `fontSizeSp` – Font size in SP (scaled pixels)

---

### SetLineHeight

**Signature:** `SetLineHeight(component, lineHeightPx)`

Sets a fixed line height for the entire text of the component.

- `component` – `Label` / `TextBox`
- `lineHeightPx` – Line height in pixels

Useful for consistent spacing in multi-line text.

---

### SetTextBulleted

**Signature:** `SetTextBulleted(component, startIndex, endIndex, gapWidthPx, bulletRadiusPx, color)`

Adds bullet styling to a text range.

- `component` – `Label` / `TextBox`
- `startIndex` – 1-based start of the bulleted range (inclusive)
- `endIndex` – 1-based end of the bulleted range (exclusive)
- `gapWidthPx` – Horizontal gap between bullet and text (pixels)
- `bulletRadiusPx` – Radius of the bullet dot (pixels)
- `color` – Integer ARGB color of the bullet

This uses `BulletSpan` so you can create custom colored bullet lists inside a single text component.
