Najjar, L. J. (2005). Accessible Java application user interface design guidelines. In HCI International 2005 Proceedings. Mahwah, NJ: Lawrence Erlbaum.




Accessible Java Application User Interface Design Guidelines
Lawrence J. Najjar, Ph.D.

BMC Software*
10431 Morado Circle
Austin, TX 78759
lawrence_najjar@bmc.com
(*Now at TandemSeven)

Abstract
Builders of Java applications need guidelines to help them design and develop accessible user interfaces.  A good foundation for developing accessibility guidelines is the United States federal government’s comprehensive Section 508 procurement regulation for accessible, interactive software applications.  This paper lists each of the relevant Section 508 accessibility requirements for interactive software applications.  After each requirement, the paper provides detailed, concrete guidelines for designing and developing Java user interfaces that conform to the accessibility requirements.

Introduction
Most accessible software user interface design requirements, such as the United States Section 508 software procurement regulation (General Services Administration, 2005), focus on the Web.  Likewise, most accessible software design resources and tools, such as the World Wide Web Consortium (World Wide Web Consortium, 2005) and Bobby (Watchfire, 2005), focus on Web user interfaces.

However, many user interface designers work on highly interactive, very complex Java applications.  These designers have very few resources and tools to help make their Java applications accessible.  For example, there is very little guidance on how to meet the very broad Section 508 accessibility requirements for software applications.

This paper lists each of the Section 508 accessibility requirements for software applications.  The two major, most relevant Section 508 sections are:

Paragraph 1194.21 Software applications and operating systems
Paragraph 1194.31 Functional performance criteria.
After each accessibility requirement, the paper describes what designers and developers can do to conform to the requirement.

1.1 Paragraph 1194.21 Software Applications and Operating Systems
These requirements describe how to make interactive software applications, such as Java applications, and operating systems more accessible.

1.1.1 Keyboard Alternatives
“(a) When software is designed to run on a system that has a keyboard, product functions shall be executable from a keyboard where the function itself or the result of performing a function can be discerned textually.” (General Services Administration, 2005)
Users who are blind must use the keyboard because they cannot see where a mouse cursor is pointing.  Users who have challenges moving their hands or making small, controlled hand movements cannot use a mouse.  Instead, these users often operate only the keyboard when using Java software applications.

With a couple of exceptions, allow users to operate only the keyboard to perform all functions.  Provide keyboard alternatives when the function (for example, rotate figure) or the result of performing a function (for example, save file confirmation) can be perceived textually by users.  For example, provide keyboard alternatives for menu functions in common drawing programs that allow users to Open, Save, Re-size, Rotate, and perform other actions on a graphic image.  The application displays these functions using text and users perceive the result of performing the functions using text.

It is not necessary to provide keyboard alternatives for tasks that require precise mouse movements that users can perceive textually only if user interface designers provide lengthy descriptions.  For example, it is not necessary to provide keyboard alternatives for tasks that allow users to create an image by selecting a paintbrush (which requires lengthy text to describe each paintbrush), picking a color, and actually drawing a design (which requires lengthy text to describe position on screen, cursor movements, resulting shape, etc.).

In particular, to provide keyboard-only access to your user interface, provide the following design features.

Define initial focus for the first logical component on every window. The initial focus is the starting point for the tabbing order on a window.  A good place to locate the initial focus is the top, left user interface component in a window.
Define a tabbing order that makes sense to users, such as moving the focus from the top-left of the window to the bottom-right of the window.  When users press the “Tab” key, move the focus forward through the tabbing order.  When users press “Shift” and “Tab” simultaneously move the focus backward through the tabbing order.
Allow users to tab between groups of related user interface controls (such as a group of related check boxes or radio buttons, or choices in a list box, combo box, or spin box).  To move between the choices in a set of related controls, allow users to press the up arrow and down arrow keys.
Allow users to press the spacebar to select a choice and the “Enter” key to perform the default action on the window (for example, activate the “OK” button on a dialog window).
On forms that include instructions, include a tab stop for the instructions.  That way, when the popular JAWS screen reader (Freedom Scientific, 2005) is in “forms” mode, users can hear the instructions for completing the form.
Allow users to press the “F10” key to move focus to the Java application window menu bar (“File,” “Edit,” “View”) at the top of the application window.  When the focus is in the menu bar, allow users to press the “Tab,” “Shift-Tab,” and right and left arrow keys to move between menu bar choices (for example, “File, “Edit,” “View”).  When the focus is on a menu bar menu, allow users to press the down arrow key or up arrow key to open the menu bar menu.  When a menu bar menu is open, allow users to press the right arrow and left arrow keys to move between and open adjacent menu bar menus.  Allow users to press the “Esc” key to close an open menu bar menu.
Provide keyboard alternatives for drag and drop functions.
If your Java application has multiple windows or panes, allow users to press “Alt-F6” to move between modeless secondary (dialog) windows and “Ctrl-F6” to move to the next child window in a multiple document interface.
Users with accessibility challenges are likely to make input errors.  It is also much more difficult, frustrating, and time-consuming for these users to correct an input error.  So, provide an easy way for users to undo at least the most recent user action.
For other keyboard operations to support, review Sun’s “Java Look and Feel Guidelines” (Sun, 2001).

1.1.2 Mnemonics
Mnemonics allow a user to press “Alt” and another (usually alphabetic) key to move focus to a user interface control and usually activate the control.  An example mnemonic is “Alt-f” to open the “File” menu bar menu.  Provide a mnemonic for each menu bar item, choice in menu bar menus, and secondary (dialog) window component in your application.  Provide a mnemonic for the most frequently used controls in a primary window.  If a long list of related radio buttons or check boxes prevents you from providing a unique mnemonic for every component in a secondary (dialog) window, then use one mnemonic to move the focus to the first component in the related group of radio buttons or check boxes, then use the up and down arrow keys to move the focus within the group of related radio buttons or check boxes.  Do not provide mnemonics for “OK” (or the default button) and “Cancel” buttons in a secondary (dialog) window.  Instead, for “OK” (or the default button) use the “Enter” key.  For “Cancel” use the “Esc” key.  Follow consistent, specific rules to define mnemonics (Microsoft, 2004; Sun, 2001).

1.1.3 Shortcut Keys
Provide shortcut keys that allow users to press “Ctrl” (usually) and another alphanumeric key (for example, “Ctrl-c”) to perform very frequent menu bar actions, even if the menus are not open.  Shortcut keys perform the menu bar action regardless of the location of the focus and without opening the menu bar.  Some shortcut keys (such as “Shift-F1” to open contextual Help) use “Shift’ instead of “Ctrl.”  A few rare shortcut keys use a direct key (such as “Delete” or “F1”) instead of an alphanumeric character.  Do not use “Alt” as a shortcut key because “Alt” is used for mnemonics.  Where possible, use standard, common shortcut keys (Microsoft, 2004; Sun, 2001).

1.1.4 Combo Boxes
In combo boxes, allow users to keep control.  Do not process each choice in a combo box as users move focus to the choice via the keyboard.  This technique causes unexpected changes in the user interface (such as opening new windows) and slows user interface response time.  Instead, allow users to press up and down arrow keys to move up and down the list of choices in a combo box.  Process a choice only after keyboard users position the focus on a choice and press the “Enter” key.  Or, process a choice only after keyboard users position the focus on a choice, press the “Enter” key, then select a button to submit the choice (for example, “Go,” “Submit,” “Enter,” “Next >”).

1.1.5 Tool Tips
Provide simple, alternative, accessible ways for users to access the text that is in a tool tip.  By default, tool tips are not available to blind users operating screen readers.  Since tool tips automatically close after four seconds, tool tips can also be a challenge for users with cognitive challenges or users for whom the language in the application is not their first language.  For example, users who were born deaf to English speaking parents probably learned sign language as their first language and English as their second language.  These users may not be able to finish reading a tool tip before the tool tip closes.

For a graphic (such as a toolbar button) that has a tool tip, provide an alternative text label that assistive technologies (such as screen readers) can read.  For a line of data in a table, provide tool tip text as a button or hyperlink labelled “Details,” “Summary,” or “Status.”

Another way to provide tool tip text is the “Action” menu bar choice.  When users select an object, the choices in the right-click (or “Shift-F10”) contextual menu also appear in the “Action” menu.  Include in the contextual menu (and automatically in the “Action” menu) a choice for “Show Details” or “Show Summary.”  When users select “Show Details” or “Show Summary,” provide a secondary (dialog) informational window that shows the tool tip text or more detailed information for the object.

1.1.6 Help
In most online help, we describe mouse techniques for performing functions (for example, “To open the contextual menu, right-click on the item”).  However, users who operate only keyboards also need help.  Keyboard users may have no idea which key or key combinations to press to perform a function.  In the Help, always describe keyboard techniques for performing the functions.  So, change “To open the contextual menu, right-click on the item” to “To open the contextual menu, right-click on the item or move focus to the item and press Shift-F10.”  Also, avoid writing mouse-centric help.  Instead of writing “Click on the Help button” use “Select the Help button.”  Mouse and keyboard users know how to select objects.

1.1.7 Accessibility Features
“(b) Applications shall not disrupt or disable activated features of other products that are identified as accessibility features, where those features are developed and documented according to industry standards.Applications also shall not disrupt or disable activated features of any operating system that are identified as accessibility features where the application programming interface for those accessibility features has been documented by the manufacturer of the operating system and is available to the product developer.” (General Services Administration, 2005)
Application accessibility features may include larger font sizes, tabular versions of information in graphs, alternative colors, keyboard operations, and captions for animations and movies.  Make sure that your application does not affect the accessibility features in other applications that users turned on and are currently running.

Operating system accessibility features may include changing the color scheme (to assist people with low vision), increasing the font size, showing a visual cue (such as flashing a window’s title bar) when an error tone is sounded (to assist persons who are deaf or hard of hearing), or providing "sticky keys" that allow a user to press key combinations (such as “Ctrl-c”) sequentially rather than simultaneously (to assist persons with dexterity challenges).  In Windows XP, you can turn on accessibility features by going to Start > Settings > Control Panel > Accessibility Options and Start > Programs > Accessories > Accessibility.  Make sure that your application does not affect the operating system accessibility features that users turned on and are currently running.

1.1.8 Focus
"(c) A well-defined on-screen indication of the current focus shall be provided that moves among interactive interface elements as the input focus changes.  The focus shall be programmatically exposed so that assistive technology can track focus and focus changes.”  (General Services Administration, 2005)
The focus, such as a light purple box around an entry field, shows users where an action will occur when users press keyboard keys.  Focus is helpful to users operating only keyboards.  Always provide obvious visual indicators for the current focus.  Programmatically expose the focus so that assistive technologies, such as screen readers and voice recognition applications, can track focus and focus changes.  When users move focus out of a window then back into the window, users may have difficulty finding the focus and moving it to the object that originally had focus.  So, when users move focus back to a window, put the focus on the object that last had the focus.

1.1.9 Information on User Interface Elements
“(d) Sufficient information about a user interface element including the identity, operation and state of the element shall be available to assistive technology.  When an image represents a program element, the information conveyed by the image must also be available in text.” (General Services Administration, 2005)
For assistive technology to work well, the information technology must have access to the information about an application’s user interface controls (or “elements”).  With this access, assistive technology can tell users the existence, location, and status of user interface elements.

In particular, provide the following design features.

Make available to assistive technologies, such as screen readers and voice recognition applications, helpful information about each user interface element including the identity, operation, and state (for example, “checked” for a checkbox, “vertical” for a scroll bar) of the user interface element.
Since some objects do not include static text (for example, text entry fields), create and associate textual labels with these objects.  In particular, always associate text fields with their labels (use the setLabelFor() method to associate the label with the text field).
Users with vision challenges may find it difficult to differentiate entry field labels from instructions, section titles, or other read-only text.  So, to help screen reader users associate an entry field label with its corresponding entry field, end each entry field label with a colon.  Also, generally place entry field labels to the left of their associated entry fields.
By default, screen readers do not read font style (for example, bold, italic) or font size.  Users of screen readers will not usually get font style or size information for the text being read by the screen reader.  Users of screen magnifiers may not be able to tell that a font or font size changed.  So, do not use changes in font or font size to convey information, indicate an action, prompt a response, or distinguish a visual element.
Provide textual alternatives for graphics that provide user interface functions.  If you use a graphic to show a user interface function (such as a toolbar button icon) or essential information (such as a graphical asterisk to identify a required field or an icon to show current system status), provide an alternative text label that assistive technologies such as screen readers can read.  Do not provide textual alternatives for graphics that are purely decorative or provide visual separation (for example, horizontal lines that separate groups of user interface controls).
To accommodate users who cannot see or cannot see well, provide an option for users to present graphical information via text.  So, provide an option for users to show the information in bar charts, line graphs, pie charts, gauges and other graphical information in a table.  Provide a table label, column labels, row labels, and, if possible, an interpretation (for example, “Required memory increasing at approximately 21% per month over past seven months.  Predict available memory will be exhausted in September, 2006.”) (Willuhn et al., 2003, Figure 3).
Users with visual challenges cannot easily identify the row header and column header that uniquely define each cell in a table.  As a result, it is difficult for users with visual challenges to understand the meaning of information in a table cell.  So, for each cell in a table, provide information that allows a screen reader to read the row header (if there is one), column header, then the information in the cell.
To identify the object or section of the user interface in which they are working, users of screen readers and screen magnifiers need textual labels.  Labels also help users with cognitive challenges and learning disabilities.  Provide a textual label for each window in the user interface, each pane in the user interface, each table in a window, each row (if appropriate) and column in a table, and each control.
1.1.10 Bitmap Image Meaning
“(e) When bitmap images are used to identify controls, status indicators, or other programmatic elements, the meaning assigned to those images shall be consistent throughout an application's performance.” (General Services Administration, 2005)
Most screen readers, such as JAWS, allow users to assign a textual name to a graphic (for example, an icon to show system status).  If the function of the graphic changes, then users get confused.  Users with low vision, learning challenges (such as dyslexia), or other cognitive challenges benefit from consistent graphics.  So, if you assign a function or meaning to a graphic, keep the same function or meaning throughout the application.

1.1.11 Textual Information
“(f) Textual information shall be provided through operating system functions for displaying text.  The minimum information that shall be made available is text content, text input caret location, and text attributes.” (General Services Administration, 2005)
Assistive technologies, such as screen readers, use operating system functions to access textual information for users.  Usually, this is not a problem because most application developers use the standard protocols of the operating system to display textual information.  However, if application developers do not use the operating system to display textual information, then assistive technologies users may not be able to access the textual information.

Designers and developers can create innovative ways to display textual information.  However, they must be sure to also use the operating system to display the textual information, including text content, text input caret location, and text attributes.

1.1.12 Contrast and Color Selections
“(g) Applications shall not override user selected contrast and color selections and other individual display attributes.” (General Services Administration, 2005)
Users with visual challenges may take advantage of system-wide accessibility features offered by popular operating systems.  To make applications easier to use, low vision users may change contrast (for example, high contrast black text on white background or low contrast black text on slate blue background), change the color scheme (for example, yellow text on black background), or increase the font size.  In Windows XP, you can turn on display accessibility features by going to Start > Settings > Control Panel > Accessibility Options > Display and Start > Programs > Accessories > Accessibility > Accessibility Wizard.  Design your application so that it does not override system-wide display options selected by users.

1.1.13 Animation
“(h) When animation is displayed, the information shall be displayable in at least one non-animated presentation mode at the option of the user.” (General Services Administration, 2005)
Animations include rapidly changing text (such as ticker text that automatically scrolls across the bottom of a window), graphics (such as animated real-time status data on a bar chart), and icons (such as a clock with moving hands to show a request is being processed).  For users with certain cognitive challenges, animations are extremely distracting and the rapid visual changes are hard to comprehend.  For blind users of screen reader software, animations are impossible to perceive.

So, generally avoid the use of animations in your applications.  If you do provide animations, include a user selectable textual alternative way to present the animated information.  Ideally, do not require users to perform an extra action to show the textual alternative.  For example, for the clock with moving hands animation, create a message dialog window that includes the clock with moving hands animation and “Request being processed” text.

Give users with cognitive challenges or users who are not familiar with the language a chance to process the animated information more slowly.  Allow users to slow the speed of the graphical animation or text.  Also, provide a way for users to stop the animation.

1.1.14 Color Coding
“(i) Color coding shall not be used as the only means of conveying information, indicating an action, prompting a response, or distinguishing a visual element.” (General Services Administration, 2005)
When you use color as the only way to communicate information, you make it difficult or impossible for users with low vision, no vision, or color deficiencies to perceive the information.  Users who cannot see have serious challenges with color-coded information because screen readers cannot read color.  Use color to enhance the user interface.  For example, use color to help users notice and identify important information.  But be sure to use color coding with another redundant communication technique such as text.  For example, in a table that lists systems, show that a system failed by placing a graphic and a textual alert beside the system name.

The use of color in graphical charts is a particular challenge.  Differentiate lines in a line graph by using different line styles (for example, solid, dotted, dashed).  Differentiate adjoining bars in a bar graph and pie slices in a pie chart by using different fill patterns (for example, angled lines, cross-hatched lines).  Where possible, show the values directly on each graph or pie slice rather than using a legend.

1.1.15 Contrast Levels
“(j) When a product permits a user to adjust color and contrast settings, a variety of color selections capable of producing a range of contrast levels shall be provided.” (General Services Administration, 2005)
Some users with visual challenges need high contrast displays.  Users with other visual challenges have trouble with high contrast displays because bright backgrounds (such as white) wash out foreground text.  Finally, since they have trouble perceiving colors, users with color deficiencies may use contrast differences to help differentiate colors.

So, users need to be able to change colors and the colors need to use a range of contrasts.  Most major operating systems allow users to change colors and contrasts.  For example, Windows XP allows users to change color settings by going to Start > Settings > Control Panel > Display > Appearance > Color Scheme.  The operating system shows the new color and contrast settings system-wide, including all applications.

In general, allow users to change color and contrast settings via the operating system.  If you need to allow users to change color and contrast settings only for your application (for example, to change color settings for a graph), then provide a variety of color settings that use a range of contrast levels.

1.1.16 Textual Information
“(k) Software shall not use flashing or blinking text, objects, or other elements having a flash or blink frequency greater than 2 Hz and lower than 55 Hz.” (General Services Administration, 2005)
Bright, flashing objects or rapidly changing geometric patterns are annoying and can trigger seizures in some people.  In particular, photosensitive epileptic seizures can occur when a very small proportion of epileptics look at flashing lights or flickering images (Epilepsy.com, 2004a, 2004c).  These flashing images can occur on computer displays, video games, and televisions (Carmant & Seshia; Epilepsy.com, 2004b; Epilepsy Action; Ishida et al, 1998; National Institute of Neurological Disorders and Stroke, 2004; Takahashi & Fujiwara, 2004) and can produce involuntary body movements and short periods of frozen staring.

So, do not use flashing objects or rapidly changing geometric shapes in your user interface.  Do not blink any objects, including text, at a frequency between 2 Hz and 55 Hz.

“(l) When electronic forms are used, the form shall allow people using assistive technology to access the information, field elements, and functionality required for completion and submission of the form, including all directions and cues.” (General Services Administration, 2005)
Assistive technologies, such as screen readers, may respond in unpredictable ways to electronic forms.  So, design your electronic forms to work well with assistive technologies.  As described elsewhere in this paper:

Provide mnemonics for all user interface controls that appear in a secondary (dialog) window.
Locate entry field labels very close to their related entry fields.  To use a familiar convention that works well with screen readers and screen magnifiers, generally put entry field labels to the left of entry fields.
To differentiate field labels from read-only text (such as section titles or instructions), put a colon at the end of each field label.
Define an initial focus on each window.
Show focus on each control.
Expose focus programmatically so assistive technologies (such as screen readers, screen magnifiers, and voice recognition applications) can use the focus.
Provide a tabbing order on each window that makes sense to users.
Allow users to tab between controls.  But in a group of related controls (such as a long list of related check boxes or radio buttons, or choices in a list box, combo box, or spin box) allow users to press the up arrow and down arrow keys to move between choices.
Provide textual labels for all user interface controls.
Make available to assistive technologies helpful information about a user interface element including the identity, operation, and state of the user interface element.
Identify required inputs by placing a graphical black asterisk before the user interface control.  For the graphic, provide alternative “Required” text.
Identify missed required inputs (for example, an entry field users did not fill in before submitting the form) by replacing the graphical black asterisk with a graphical red asterisk and the alternative “Check this required input” text.
To prevent errors, use hints to tell a user what to type or select.  Make it easy for users with accessibility challenges to identify and use the hints.  Write your code so that the screen reader reads the hint before users have to enter information or make a choice.
1.2 Paragraph 1194.31 Functional Performance Criteria
These requirements are related to the way an interactive software application works.

1.2.1 User Vision
“(a) At least one mode of operation and information retrieval that does not require user vision shall be provided, or support for assistive technology used by people who are blind or visually impaired shall be provided.” (General Services Administration, 2005)
The assistive technology that is most popular for software users with visual challenges is the screen reader.  The most popular screen reader among users with visual challenges is JAWS Professional (Freedom Scientific, 2005).  JAWS is also the only popular screen reader that works with Java applications.

Make sure that your application is both accessible and usable by users with visual challenges.  Use familiar accessibility conventions (for example, JAWS key commands).  Use accessible interaction techniques that are simple and obvious.  If you must use unique key combinations, document them in the accessibility sections of the online help and the user guide.

Test every window and every function to confirm that JAWS users can perform all application functions.

Ideally, ask representative users who are blind or visually impaired to perform typical tasks with your application using JAWS.  Ask these users to give you feedback on usability.  Include tasks such as installing the application, opening the application, customizing the application, using the application (including retrieving information), reading product support documentation, and contacting customer support for assistance.

1.2.2 Visual Acuity
“(b) At least one mode of operation and information retrieval that does not require visual acuity greater than 20/70 shall be provided in audio and enlarged print output working together or independently, or support for assistive technology used by people who are visually impaired shall be provided.” (General Services Administration, 2005)
The assistive technology that is most popular for software users who can see, but not well (“low vision”) is the screen magnifier.  The most popular screen magnifier among users with low vision is ZoomText (Ai Squared, 2005).

Make sure your application works well with ZoomText.  Test every window and every function to confirm that ZoomText users can see all user interface controls and perform all application functions.  Ideally, ask representative users who have low vision to perform typical tasks with your application using ZoomText.  Ask these users to give you feedback on accessibility and usability.  Include tasks such as installing the application, opening the application, customizing the application, using the application (including retrieving information), reading product support documentation, and contacting customer support for assistance.

1.2.3 User Hearing
“(c) At least one mode of operation and information retrieval that does not require user hearing shall be provided, or support for assistive technology used by people who are deaf or hard of hearing shall be provided." (General Services Administration, 2005)
The Windows operating system provides settings to support users with hearing challenges.  Turn on the Windows SoundSentry visual warning accessibility option.  In Windows XP, it is available by going to Start > Settings > Control Panel > Accessibility Options > Sound.  Make sure that the Windows SoundSentry visual warning accessibility option works with your application when an error occurs and a tone usually sounds (for example, when users type an alphabetic character in a numeric-only field).

Make sure all application functions, alerts, and information retrieval tasks are available visually and do not require users to be able to hear.  For example, provide textual alternatives for any spoken words.  If you cannot do this with your application, then provide at least one mode of operation and information retrieval that does not require user hearing, or provide support for assistive technology used by people who are deaf or hard of hearing.

1.2.4 Audio Information
“(d) Where audio information is important for the use of a product, at least one mode of operation and information retrieval shall be provided in an enhanced auditory fashion, or support for assistive hearing devices shall be provided.” (General Services Administration, 2005)
Do not make audio information important for the use of a product.  So, do not use sound as the only way to communicate information (for example sounding an auditory alert when users type an alphabetic character in a numeric-only entry field).  Users who are deaf cannot hear auditory alerts.  Users who cannot see and are using screen readers may not hear auditory alerts over the sound of the screen reader reading text aloud.

Make sure that the Windows SoundSentry visual warning accessibility option works when an error occurs on a window in your application.  In Windows XP, SoundSentry is available by going to Start > Settings > Control Panel > Accessibility Options > Sound.

1.2.5 User Speech
“(e) At least one mode of operation and information retrieval that does not require user speech shall be provided, or support for assistive technology used by people with disabilities shall be provided.” (General Services Administration, 2005)
Some users (such as users who cannot hear or users with cerebral palsy) have challenges speaking clearly.  These users cannot use speech recognition to operate a software application.  So, do not require user speech to use your application.

1.2.6 Motor Control
“(f) At least one mode of operation and information retrieval that does not require fine motor control or simultaneous actions and that is operable with limited reach and strength shall be provided." (General Services Administration, 2005)
To select small items, users need fine motor control of a mouse.  Fine motor control is difficult for users with muscular dystrophy, cerebral palsy, or other physical challenges.  To perform simultaneous actions, such as pressing several keyboard keys at the same time (for example, “Ctrl-c”), users need to be able to use multiple fingers simultaneously.  This is not possible for users who are partially paralyzed and use a mouthstick or head pointer.  To use a mouse and to press multiple keyboard keys simultaneously, users need good reach and strength.  Users with muscular wasting diseases such as amyotrophic lateral sclerosis, myasthenia gravis or who have had certain strokes may not be able to perform these tasks.

So, as described in the Keyboard Alternatives section, make all application functions available to users who can operate only a keyboard, not a mouse.  To accommodate users who have difficulty pressing several keys simultaneously, confirm that the Windows StickyKeys accessibility option works in your application.  StickyKeys allows users to press keys sequentially, but send them simultaneously to applications.  In Windows XP, the Windows Sticky Key accessibility option is available at Start > Settings > Control Panel > Accessibility Options > Keyboard.

Some users with muscle control challenges may have trouble lifting off a key before it starts to repeat.  One way to accommodate these users is to change the keyboard settings so the application ignores repeated keystrokes.  In Windows XP, the FilterKeys accessibility option is available at Start > Settings > Control Panel > Accessibility Options > Keyboard.  Confirm that users can cause the application to ignore and slow down the key press repeat rate in the application by using the Windows FilterKeys accessibility settings.

1.3 Conclusion
To follow the guidelines in this paper, application teams must do some extra design, development, and test work.  However, when this work is done properly, people with disabilities can use Java applications.  Accessible Java applications are also more attractive to customers, such as government agencies, who require accessible software for their users.  Accessibility is good for people and good for business.

1.4 References
Ai Squared (2005). Zoom Text 8.1 magnifier. Retrieved February 11, 2005, from http://www.aisquared.com/Products/ZoomText8_mag/Z8Mag.htm

Epilepsy.com (2004a). Frequently asked questions. Retrieved February 10, 2005, from http://www.epilepsy.com/info/family_faq.html

Epilepsy.com (2004b). Nintendo games may cause epileptic seizures in photosensitive children – epilepsy. Retrieved February 10, 2005 from http://www.epilepsy.com/newsfeed/pr_1085405403.html

Epilepsy.com (2004c, February). Reflex epilepsies. Retrieved February 10, 2005, from http://www.epilepsy.com/epilepsy/epilepsy_reflex.html

Freedom Scientific (2005). JAWS for Windows. Retrieved February 8, 2005, from http://www.freedomscientific.com/fs_products/software_jaws.asp

General Services Administration (2005). Section 508. Retrieved February 8, 2005, from http://www.section508.gov/index.cfm?FuseAction=Content&ID=12

Microsoft (2004). Official guidelines for user interface developers and designers. Retrieved February 10, 2005, from http://msdn.microsoft.com/library/default.asp?url=/library/en-us/dnwue/html/welcome.asp

ScanSoft (2005). Dragon NaturallySpeaking 8 Professional. Retrieved February 11, 2005, from http://www.scansoft.com/naturallyspeaking/professional/

Sun (2001). Java look and feel design guidelines (2nd ed.). Boston: Addison-Wesley. Retrieved February 10, 2005, from http://java.sun.com/products/jlf/ed2/book/index.html

Watchfire (2005). Welcome to Bobby. Retrieved February 8, 2005, from http://bobby.watchfire.com/bobby/html/en/index.jsp

Willuhn, D., Schulz, C., Knoth-Weber, L., Feger, S., & Saillet, Y. (2003). Developing accessible software for data visualization. IBM Systems Journal, 42(4), 652-668. Retrieved February 10, 2005, from http://www.research.ibm.com/journal/sj/424/willuhn.pdf

World Wide Web Consortium (2005). Web accessibility initiative (WAI). Retrieved February 8, 2005, from http://www.w3c.org/WAI/

1.5 Biography
Lawrence Najjar is an interaction designer with over 20 years of experience.  He worked on various Java database applications at BMC, Home Depot’s online store at iXL, and the US air traffic control system at IBM.  He has a Ph.D. in engineering psychology from the Georgia Institute of Technology.

 

© 2005 Lawrence Erlbaum Associates, Inc. To purchase the book containing this paper, go to Lawrence Erlbaum Associates, Inc.