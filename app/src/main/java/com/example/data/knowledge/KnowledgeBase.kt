package com.example.data.knowledge

import com.example.data.model.QuestionCategory
import com.example.data.model.QuestionItem

object KnowledgeBase {

    val allQuestions: List<QuestionItem> = listOf(
        // ================= SCIENCE & NATURE =================
        QuestionItem(
            id = "sci_1",
            questionEn = "Why is the sky blue during the day?",
            questionHi = "दिन के समय आकाश नीला क्यों दिखाई देता है?",
            answerEn = "The sky appears blue due to a phenomenon called Rayleigh scattering. Sunlight consists of all colors of the rainbow. When sunlight reaches Earth's atmosphere, it collides with tiny gas molecules (mostly nitrogen and oxygen). Shorter wavelengths of light (blue and violet) are scattered much more strongly in all directions than longer wavelengths (red and yellow). Since human eyes are far more sensitive to blue light than violet, the sky looks bright blue to us.",
            answerHi = "आकाश का नीला दिखना 'रेले प्रकीर्णन' (Rayleigh Scattering) के कारण होता है। सूर्य का प्रकाश सात रंगों से मिलकर बना होता है। जब सूर्य की किरणें वायुमंडल में मौजूद नाइट्रोजन और ऑक्सीजन के सूक्ष्म कणों से टकराती हैं, तो नीले और बैंगनी रंग की तरंगदैर्ध्य (wavelength) छोटी होने के कारण सबसे अधिक फैलती हैं। चूंकि मानव आंखें नीले रंग के प्रति अधिक संवेदनशील होती हैं, इसलिए हमें आकाश नीला दिखाई देता है।",
            category = QuestionCategory.SCIENCE,
            difficulty = "Easy",
            keyTakeawayEn = "Rayleigh scattering scatters short blue wavelengths across the atmosphere.",
            keyTakeawayHi = "रेले प्रकीर्णन के कारण छोटी तरंगदैर्ध्य वाला नीला प्रकाश वायुमंडल में सबसे ज्यादा बिखरता है।",
            funFactEn = "On Mars, the sky appears butterscotch or reddish-pink during the day and blue at sunset!",
            funFactHi = "मंगल ग्रह पर दिन में आकाश लाल-भूरा और सूर्यास्त के समय नीला दिखाई देता है!",
            tags = listOf("physics", "atmosphere", "light", "sky", "rayleigh")
        ),
        QuestionItem(
            id = "sci_2",
            questionEn = "How do plants convert sunlight into food during photosynthesis?",
            questionHi = "प्रकाश संश्लेषण (Photosynthesis) के दौरान पौधे धूप से भोजन कैसे बनाते हैं?",
            answerEn = "Plants use chlorophyll (the green pigment in chloroplasts) to capture sunlight energy. They take in carbon dioxide (CO2) from the air through microscopic pores called stomata and absorb water (H2O) from soil through roots. Using light energy, they convert CO2 and H2O into glucose (C6H12O6) for energy and release oxygen (O2) into the atmosphere as a byproduct. Formula: 6CO2 + 6H2O + Sunlight -> C6H12O6 + 6O2.",
            answerHi = "पौधे अपनी पत्तियों में मौजूद क्लोरोफिल (हरे वर्णक) की सहायता से सूर्य के प्रकाश की ऊर्जा को ग्रहण करते हैं। वे जड़ों से जल (H2O) और पत्तियों के रंध्रों (stomata) से कार्बन डाइऑक्साइड (CO2) लेते हैं। प्रकाश ऊर्जा का उपयोग करके वे इसे ग्लूकोज (शर्करा) में बदलते हैं और प्राणवायु ऑक्सीजन (O2) को वायुमंडल में छोड़ते हैं। रासायनिक सूत्र: 6CO2 + 6H2O + प्रकाश -> C6H12O6 + 6O2।",
            category = QuestionCategory.SCIENCE,
            difficulty = "Medium",
            keyTakeawayEn = "Plants use sunlight, water, and CO2 to produce glucose and release life-giving oxygen.",
            keyTakeawayHi = "पौधे सूर्य के प्रकाश, पानी और CO2 से ग्लूकोज बनाते हैं और ऑक्सीजन छोड़ते हैं।",
            funFactEn = "Over 50% of the world's oxygen is produced not by rainforests, but by microscopic oceanic phytoplankton!",
            funFactHi = "पृथ्वी की 50% से अधिक ऑक्सीजन जंगलों से नहीं, बल्कि समुद्र में मौजूद सूक्ष्म फाइटोप्लांकटन से आती है!",
            tags = listOf("biology", "plants", "photosynthesis", "oxygen", "chlorophyll")
        ),
        QuestionItem(
            id = "sci_3",
            questionEn = "Why do onions make us cry when we cut them?",
            questionHi = "प्याज काटते समय हमारी आंखों में आंसू क्यों आते हैं?",
            answerEn = "When an onion is sliced, its cells break open, mixing enzymes (alliinases) with amino acid sulfoxides. This reaction produces a volatile sulfur compound called syn-propanethial-S-oxide. As this gas evaporates into the air and reaches our eyes, it stimulates the lacrimal glands to produce protective tears to flush out the irritant.",
            answerHi = "प्याज काटने पर उसकी कोशिकाएं टूटती हैं जिससे 'एलीनेस' एंजाइम और अमीनो एसिड सल्फाॅक्साइड आपस में मिलते हैं। इस रासायनिक क्रिया से 'सिन-प्रोपेनेथियल-एस-ऑक्साइड' नामक एक वाष्पशील सल्फर गैस बनती है। यह गैस हवा में उड़कर आंखों तक पहुंचती है, जिससे आंखों की अश्रु ग्रंथियां जलन मिटाने के लिए आंसू बहाने लगती हैं।",
            category = QuestionCategory.SCIENCE,
            difficulty = "Easy",
            keyTakeawayEn = "Volatile sulfur gas (syn-propanethial-S-oxide) irritates eye nerves, triggering tear glands.",
            keyTakeawayHi = "सल्फर गैस आंखों के तंत्रिका तंत्र में जलन पैदा करती है जिससे अश्रु ग्रंथियां सक्रिय हो जाती हैं।",
            funFactEn = "Chilling the onion before slicing slows down the enzymatic reaction and prevents tears!",
            funFactHi = "काटने से पहले प्याज को 15 मिनट फ्रिज में रखने से गैस कम निकलती है और आंसू नहीं आते!",
            tags = listOf("chemistry", "cooking", "onion", "tears", "enzymes")
        ),
        QuestionItem(
            id = "sci_4",
            questionEn = "What is the speed of light in vacuum?",
            questionHi = "निर्वात (Vacuum) में प्रकाश की गति कितनी होती है?",
            answerEn = "The speed of light in a vacuum is exactly 299,792,458 meters per second (approximately 300,000 km/s or 186,282 miles per second). According to Albert Einstein's theory of Special Relativity, this speed (denoted as 'c') represents the ultimate universal speed limit at which information and massless particles can travel.",
            answerHi = "निर्वात में प्रकाश की गति ठीक 299,792,458 मीटर प्रति सेकंड होती है (लगभग 3 लाख किलोमीटर प्रति सेकंड)। अल्बर्ट आइंस्टीन के विशेष सापेक्षता सिद्धांत के अनुसार, इसे 'c' से दर्शाया जाता है और यह ब्रह्मांड में किसी भी सूचना या द्रव्यमान-रहित कण के यात्रा करने की अधिकतम सीमा है।",
            category = QuestionCategory.SCIENCE,
            difficulty = "Medium",
            keyTakeawayEn = "Light travels at roughly 300,000 km/s in vacuum — the cosmic speed limit.",
            keyTakeawayHi = "प्रकाश निर्वात में लगभग 3,00,000 किमी/सेकंड की गति से चलता है।",
            funFactEn = "Light from the Sun takes roughly 8 minutes and 20 seconds to reach the Earth!",
            funFactHi = "सूर्य से प्रकाश को पृथ्वी तक पहुँचने में लगभग 8 मिनट 20 सेकंड का समय लगता है!",
            tags = listOf("physics", "speed of light", "einstein", "astronomy")
        ),

        // ================= GENERAL KNOWLEDGE =================
        QuestionItem(
            id = "gk_1",
            questionEn = "Which is the longest river in the world?",
            questionHi = "विश्व की सबसे लम्बी नदी कौन सी है?",
            answerEn = "The Nile River in northeastern Africa is officially recognized as the world's longest river, stretching approximately 6,650 kilometers (4,132 miles). It flows through 11 countries including Egypt, Sudan, and Uganda before emptying into the Mediterranean Sea. (The Amazon River in South America is the largest by water volume discharge).",
            answerHi = "पूर्वोत्तर अफ्रीका में स्थित 'नील नदी' (Nile River) दुनिया की सबसे लम्बी नदी मानी जाती है, जिसकी कुल लम्बाई लगभग 6,650 किलोमीटर (4,132 मील) है। यह मिस्र, सूडान सहित 11 देशों से होकर भूमध्य सागर में गिरती है। (जबकि दक्षिण अमेरिका की अमेज़न नदी जल भराव की दृष्टि से विश्व की सबसे बड़ी नदी है)।",
            category = QuestionCategory.GK,
            difficulty = "Easy",
            keyTakeawayEn = "Nile is the longest river (6,650 km), while Amazon carries the greatest water volume.",
            keyTakeawayHi = "नील सबसे लम्बी नदी (6,650 किमी) है और अमेज़न जल मात्रा में सबसे विशाल है।",
            funFactEn = "Ancient Egyptian civilization developed almost entirely along the fertile banks of the Nile!",
            funFactHi = "प्राचीन मिस्र की पूरी सभ्यता नील नदी के उपजाऊ किनारों पर ही विकसित हुई थी!",
            tags = listOf("geography", "nile", "rivers", "world records", "africa")
        ),
        QuestionItem(
            id = "gk_2",
            questionEn = "Which country is known as the 'Land of the Rising Sun'?",
            questionHi = "किस देश को 'उगते सूरज की भूमि' (Land of Rising Sun) कहा जाता है?",
            answerEn = "Japan is known as the 'Land of the Rising Sun'. In the Japanese language, the country is called 'Nihon' or 'Nippon', which literally translates to 'origin of the sun'. Because Japan lies to the far east of the Asian continent, from China's perspective the sun appeared to rise from the direction of Japan.",
            answerHi = "जापान को 'उगते सूरज की भूमि' कहा जाता है। जापानी भाषा में इसे 'निहोन' (Nihon) या 'निप्पॉन' कहते हैं, जिसका शाब्दिक अर्थ 'सूर्य का उद्गम' होता है। चूंकि जापान एशियाई महाद्वीप के सबसे पूर्व में स्थित है, इसलिए चीन और अन्य पड़ोसी देशों के दृष्टिकोण से सूर्य जापान की दिशा से ही उगता दिखता था।",
            category = QuestionCategory.GK,
            difficulty = "Easy",
            keyTakeawayEn = "Japan's native name 'Nihon' literally means the origin of the sun.",
            keyTakeawayHi = "जापान का मूल नाम 'निहोन' है जिसका अर्थ सूर्य का उद्गम होता है।",
            funFactEn = "The red circle on Japan's national flag represents the rising sun disc (Hinomaru)!",
            funFactHi = "जापान के राष्ट्रीय ध्वज का लाल गोला उगते हुए सूर्य (हिनोमारु) का प्रतीक है!",
            tags = listOf("geography", "japan", "asia", "nicknames")
        ),
        QuestionItem(
            id = "gk_3",
            questionEn = "What is the capital of Australia?",
            questionHi = "ऑस्ट्रेलिया की राजधानी कौन सी है?",
            answerEn = "Canberra is the capital of Australia. Many people incorrectly assume Sydney or Melbourne is the capital. Canberra was specially chosen as a compromise in 1908 because Sydney and Melbourne had an intense rivalry over which city should become the nation's capital.",
            answerHi = "ऑस्ट्रेलिया की राजधानी 'कैनबरा' (Canberra) है। अक्सर लोग सिडनी या मेलबर्न को राजधानी समझ लेते हैं। दरअसल 1908 में सिडनी और मेलबर्न के बीच राजधानी बनने को लेकर भारी विवाद था, इसलिए दोनों के बीच स्थित कैनबरा को एक समझौते के तहत नई राजधानी चुना गया।",
            category = QuestionCategory.GK,
            difficulty = "Easy",
            keyTakeawayEn = "Canberra is the planned capital city of Australia, chosen as a compromise between Sydney and Melbourne.",
            keyTakeawayHi = "कैनबरा ऑस्ट्रेलिया की नियोजित राजधानी है, जिसे सिडनी और मेलबर्न के समझौते से बनाया गया।",
            funFactEn = "Canberra is an entirely planned city designed by American architects Walter Burley Griffin and Marion Mahony Griffin!",
            funFactHi = "कैनबरा पूरी तरह से एक नियोजित (Planned) शहर है जिसे अमेरिकी वास्तुकारों ने डिजाइन किया था!",
            tags = listOf("capitals", "australia", "world gk", "canberra")
        ),
        QuestionItem(
            id = "gk_4",
            questionEn = "Who was the first human to travel into outer space?",
            questionHi = "अंतरिक्ष में जाने वाले पहले मानव कौन थे?",
            answerEn = "Soviet cosmonaut Yuri Gagarin was the first human in space. On April 12, 1961, aboard the Vostok 1 spacecraft, he completed a full orbit around Earth in 108 minutes, uttering his famous line 'Poyekhali!' ('Let's go!').",
            answerHi = "सोवियत अंतरिक्ष यात्री यूरी गगारिन (Yuri Gagarin) अंतरिक्ष में जाने वाले पहले मानव थे। 12 अप्रैल 1961 को वोस्तोक-1 (Vostok 1) अंतरिक्ष यान से उन्होंने 108 मिनट में पृथ्वी की एक पूरी कक्षा की परिक्रमा की थी।",
            category = QuestionCategory.GK,
            difficulty = "Medium",
            keyTakeawayEn = "Yuri Gagarin orbited Earth in 1961 aboard Vostok 1.",
            keyTakeawayHi = "यूरी गगारिन ने 12 अप्रैल 1961 को वोस्तोक-1 से अंतरिक्ष की पहली यात्रा की थी।",
            funFactEn = "India's first astronaut in space was Rakesh Sharma in April 1984 aboard Soyuz T-11!",
            funFactHi = "भारत के पहले अंतरिक्ष यात्री विंग कमांडर राकेश शर्मा थे जो अप्रैल 1984 में सोयुज टी-11 से अंतरिक्ष गए थे!",
            tags = listOf("space", "gagarin", "history", "astronaut")
        ),

        // ================= MATHEMATICS & LOGIC =================
        QuestionItem(
            id = "math_1",
            questionEn = "What is the Pythagorean Theorem and where is it used?",
            questionHi = "पाइथागोरस प्रमेय (Pythagorean Theorem) क्या है और इसका उपयोग कहाँ होता है?",
            answerEn = "The Pythagorean Theorem states that in any right-angled triangle (triangle with a 90-degree angle), the square of the hypotenuse (the longest side opposite the right angle) is equal to the sum of the squares of the other two sides. Formula: a² + b² = c² (where c is the hypotenuse). It is used everywhere in navigation, architecture, construction, computer graphics, and physics to calculate direct distances.",
            answerHi = "पाइथागोरस प्रमेय के अनुसार किसी भी समकोण त्रिभुज (जिसका एक कोण 90° हो) में कर्ण (hypotenuse) का वर्ग, आधार और लंब (अन्य दो भुजाओं) के वर्गों के योग के बराबर होता है। सूत्र: a² + b² = c² (जहाँ c कर्ण है)। इसका उपयोग भवन निर्माण, नेविगेशन, दूरी मापने और कंप्यूटर ग्राफिक्स में किया जाता है।",
            category = QuestionCategory.MATHEMATICS,
            difficulty = "Easy",
            keyTakeawayEn = "In right triangles: a² + b² = c². Essential for calculating spatial distances.",
            keyTakeawayHi = "समकोण त्रिभुज में कर्ण का वर्ग = लंब का वर्ग + आधार का वर्ग (a² + b² = c²)।",
            funFactEn = "The ancient Indian mathematician Baudhayana described this geometric relation in the Sulba Sutras centuries before Pythagoras!",
            funFactHi = "प्राचीन भारतीय गणितज्ञ बौधायन ने पाइथागोरस से सदियों पहले शुल्ब सूत्रों में इस प्रमेय का उल्लेख किया था!",
            tags = listOf("math", "geometry", "pythagoras", "triangles", "formulas")
        ),
        QuestionItem(
            id = "math_2",
            questionEn = "What is the value of Pi (π) and why is it called an irrational number?",
            questionHi = "पाई (π) का मान क्या है और इसे अपरिमेय संख्या (Irrational Number) क्यों कहते हैं?",
            answerEn = "Pi (π) is the constant ratio of a circle's circumference to its diameter (Circumference / Diameter). Its approximate decimal value is 3.14159... or fractionally 22/7. It is classified as an irrational number because its decimal representation never ends and never repeats in any pattern. It cannot be written as a simple fraction a/b with whole numbers.",
            answerHi = "पाई (π) किसी भी वृत्त की परिधि (Circumference) और उसके व्यास (Diameter) का स्थिर अनुपात है। इसका दशमलव मान लगभग 3.14159... तथा भिन्न मान 22/7 माना जाता है। इसे अपरिमेय संख्या इसलिए कहा जाता है क्योंकि इसके दशमलव अंक कभी समाप्त नहीं होते और न ही किसी निश्चित क्रम में दोहराते हैं।",
            category = QuestionCategory.MATHEMATICS,
            difficulty = "Medium",
            keyTakeawayEn = "Pi is Circumference / Diameter; its decimals extend infinitely without repeating.",
            keyTakeawayHi = "पाई = परिधि / व्यास; इसके दशमलव अंक कभी खत्म नहीं होते और अनंत तक चलते हैं।",
            funFactEn = "March 14 (3/14) is celebrated worldwide as Pi Day, matching the first three digits 3.14!",
            funFactHi = "14 मार्च (3/14) को पूरी दुनिया में 'पाई दिवस' मनाया जाता है!",
            tags = listOf("math", "pi", "geometry", "irrational numbers", "circle")
        ),
        QuestionItem(
            id = "math_3",
            questionEn = "Quick shortcut trick to calculate percentages easily in mind?",
            questionHi = "मन में प्रतिशत (Percentage) आसानी से निकालने की शॉर्टकट ट्रिक क्या है?",
            answerEn = "Trick 1: Use the reversible rule: x% of y = y% of x! For example, 16% of 50 sounds tricky, but 50% of 16 is super easy = 8! Trick 2: Break down into 10% and 1%. To find 15% of 240: 10% of 240 is 24, half of that (5%) is 12, so 24 + 12 = 36!",
            answerHi = "ट्रिक 1 (रिवर्सिबल नियम): x% of y = y% of x! उदाहरण के लिए '50 का 16%' निकालना कठिन लग सकता है, लेकिन '16 का 50%' बहुत सरल है = 8! ट्रिक 2 (10% और 1% में तोड़ें): 240 का 15% निकालना हो तो 10% = 24 और 5% = 12, दोनों को जोड़ें: 24 + 12 = 36!",
            category = QuestionCategory.MATHEMATICS,
            difficulty = "Easy",
            keyTakeawayEn = "Percentages are reversible: x% of y = y% of x. Split into 10% and 5% blocks.",
            keyTakeawayHi = "प्रतिशत आपस में बदले जा सकते हैं: x का y% = y का x%। इसे 10% और 5% में बांटकर हल करें।",
            funFactEn = "This reversible trick works for every pair of numbers without exception!",
            funFactHi = "यह रिवर्सिबल फॉर्मूला सभी संख्याओं के लिए हमेशा 100% सही काम करता है!",
            tags = listOf("math", "shortcuts", "tricks", "percentage", "mental math")
        ),

        // ================= CURIOSITY & WHY/HOW =================
        QuestionItem(
            id = "cur_1",
            questionEn = "Why do humans yawn and why is yawning contagious?",
            questionHi = "इंसान जम्हाई (Yawn) क्यों लेते हैं और जम्हाई संक्रामक (देखा-देखी) क्यों होती है?",
            answerEn = "Scientific research shows that yawning acts as a brain-cooling mechanism. Inhaling a large volume of air cools the blood vessels leading to the brain, enhancing alertness when tired or bored. Yawning is contagious due to mirror neurons in the brain associated with social bonding and empathy — seeing or hearing someone yawn unconsciously triggers the same motor pattern in empathetic brains.",
            answerHi = "वैज्ञानिक अनुसंधानों के अनुसार जम्हाई मस्तिष्क को ठंडा (Brain cooling) रखने की एक प्राकृतिक प्रक्रिया है। गहरी सांस लेने से ठंडी हवा मस्तिष्क की रक्त वाहिकाओं को ठंडक पहुंचाकर सतर्कता बढ़ाती है। जम्हाई का संक्रामक होना दिमाग के 'मिरर न्यूरॉन्स' (Mirror Neurons) और सहानुभूति (Empathy) से जुड़ा है। जब हम किसी को जम्हाई लेते देखते हैं तो हमारा दिमाग भी सामाजिक जुड़ाव के कारण वही प्रतिक्रिया दोहराता है।",
            category = QuestionCategory.CURIOSITY,
            difficulty = "Easy",
            keyTakeawayEn = "Yawning cools down brain temperature; contagious yawning is powered by empathy mirror neurons.",
            keyTakeawayHi = "जम्हाई मस्तिष्क के तापमान को नियंत्रित करती है; दूसरों को देखकर जम्हाई आना सहानुभूति और मिरर न्यूरॉन्स का परिणाम है।",
            funFactEn = "Even reading or thinking about yawning can make you yawn right now!",
            funFactHi = "जम्हाई के बारे में पढ़ने या सोचने मात्र से भी इंसान को तुरंत जम्हाई आ सकती है!",
            tags = listOf("curiosity", "biology", "brain", "yawn", "psychology")
        ),
        QuestionItem(
            id = "cur_2",
            questionEn = "Why does ice float on water instead of sinking?",
            questionHi = "बर्फ पानी में डूबने की बजाय तैरती क्यों है?",
            answerEn = "Most substances become denser when they freeze into solids, but water is a remarkable exception. When liquid water cools below 4°C (39.2°F), its hydrogen bonds arrange molecules into a rigid, open hexagonal crystal lattice. This lattice holds molecules farther apart than in liquid state, making ice roughly 9% less dense than liquid water. Hence, ice floats!",
            answerHi = "अधिकतर पदार्थ जमने पर भारी और सघन हो जाते हैं, लेकिन पानी के साथ ऐसा नहीं होता। जब पानी 4°C से नीचे ठंडा होता है, तो उसके हाइड्रोजन बॉन्ड एक विशेष षट्कोणीय (Hexagonal) जालीदार संरचना बना लेते हैं। इस जाली के बीच खाली जगह रहने के कारण बर्फ का घनत्व सामान्य पानी से लगभग 9% कम हो जाता है, इसलिए बर्फ पानी पर तैरती है।",
            category = QuestionCategory.CURIOSITY,
            difficulty = "Medium",
            keyTakeawayEn = "Water expands when freezing due to hexagonal hydrogen bonding, making ice less dense than water.",
            keyTakeawayHi = "जमने पर पानी की हाइड्रोजन बॉन्डिंग फैलती है जिससे बर्फ का घनत्व तरल पानी से कम रहता है।",
            funFactEn = "If ice didn't float, lakes and oceans would freeze solid from the bottom up, killing all marine life!",
            funFactHi = "यदि बर्फ न तैरती तो झीलें और समुद्र नीचे से जम जाते और जलीय जीवन पूरी तरह नष्ट हो जाता!",
            tags = listOf("curiosity", "water", "ice", "density", "chemistry")
        ),
        QuestionItem(
            id = "cur_3",
            questionEn = "Why do migratory birds fly in a V-formation?",
            questionHi = "प्रवासी पक्षी आसमान में 'V' आकार में क्यों उड़ते हैं?",
            answerEn = "Birds fly in a V-formation primarily for aerodynamic energy conservation. When the front bird flaps its wings, it creates an upward airflow ('upwash') behind its wingtips. Birds flying slightly behind and to the side catch this lift, reducing heart rate and saving up to 20-30% of their flight energy during long migrations. It also allows birds to maintain clear visual communication.",
            answerHi = "पक्षी 'V' आकार में मुख्य रूप से ऊर्जा बचाने (Aerodynamic efficiency) के लिए उड़ते हैं। जब आगे उड़ने वाला पक्षी पंख फड़फड़ाता है, तो उसके पंखों के किनारों से ऊपर उठती हवा (Upwash) पैदा होती है। पीछे उड़ने वाले पक्षी इस हवा के सहारे कम मेहनत में उड़ पाते हैं, जिससे उनकी 20-30% तक ऊर्जा बचती है। साथ ही वे एक-दूसरे को स्पष्ट देख पाते हैं।",
            category = QuestionCategory.CURIOSITY,
            difficulty = "Easy",
            keyTakeawayEn = "V-formation catches aerodynamic upwash, saving up to 30% flight energy for migrating flocks.",
            keyTakeawayHi = "V-आकार से पीछे उड़ने वाले पक्षियों को हवा का सहारा मिलता है जिससे लंबी उड़ान में ऊर्जा बचती है।",
            funFactEn = "The lead bird rotates regularly when tired so that no single bird exhausts itself!",
            funFactHi = "जब आगे चलने वाला पक्षी थक जाता है तो वह पीछे आ जाता है और दूसरा पक्षी आगे का नेतृत्व संभाल लेता है!",
            tags = listOf("birds", "nature", "aerodynamics", "flight", "curiosity")
        ),

        // ================= TECHNOLOGY & AI =================
        QuestionItem(
            id = "tech_1",
            questionEn = "What is Artificial Intelligence (AI) and how does Machine Learning work?",
            questionHi = "आर्टिफिशियल इंटेलिजेंस (AI) क्या है और मशीन लर्निंग कैसे काम करती है?",
            answerEn = "Artificial Intelligence (AI) is the simulation of human intelligence by computers, enabling machines to perceive, reason, learn, and solve problems. Machine Learning (ML) is a subset of AI where systems learn patterns from vast amounts of data rather than following static hand-coded rules. For example, by analyzing millions of photos of cats, an ML algorithm figures out the distinguishing features on its own.",
            answerHi = "आर्टिफिशियल इंटेलिजेंस (AI) का अर्थ कंप्यूटर या मशीनों द्वारा मानवीय बुद्धिमत्ता (सोचने, सीखने और निर्णय लेने की क्षमता) का प्रदर्शन है। मशीन लर्निंग (ML) इसका वह हिस्सा है जिसमें कंप्यूटर को कोई काम नियम रटाकर नहीं, बल्कि भारी मात्रा में डेटा (Data) देकर सिखाया जाता है। उदाहरण के लिए लाखों तस्वीरों को देखकर मशीन खुद बिल्ली और कुत्ते की पहचान करना सीख जाती है।",
            category = QuestionCategory.TECHNOLOGY,
            difficulty = "Medium",
            keyTakeawayEn = "AI simulates intelligence; Machine Learning learns patterns directly from historical data.",
            keyTakeawayHi = "AI मशीनी बुद्धिमत्ता है, जबकि मशीन लर्निंग डेटा के पैटर्न से खुद सीखने की तकनीक है।",
            funFactEn = "The term 'Artificial Intelligence' was first coined in 1956 by computer scientist John McCarthy at the Dartmouth Workshop!",
            funFactHi = "1956 में जॉन मैकार्थी ने पहली बार 'आर्टिफिशियल इंटेलिजेंस' शब्द का उपयोग किया था!",
            tags = listOf("tech", "ai", "machine learning", "computers", "future")
        ),
        QuestionItem(
            id = "tech_2",
            questionEn = "What is the difference between RAM and ROM in smartphones and computers?",
            questionHi = "मोबाइल और कंप्यूटर में RAM और ROM में क्या अंतर होता है?",
            answerEn = "RAM (Random Access Memory) is high-speed temporary/volatile memory that stores data currently in use by active apps. When the device restarts, RAM is wiped clean. ROM (Read-Only Memory / Flash Storage) is non-volatile permanent storage holding the operating system, firmware, photos, and installed apps even when powered off. More RAM means smoother multitasking; more ROM/Storage means saving more files.",
            answerHi = "RAM (रैंडम एक्सेस मेमोरी) तेज गति वाली अस्थाई मेमोरी है, जो वर्तमान में चल रहे ऐप्स के डेटा को प्रोसेस करती है। फोन बंद होते ही RAM खाली हो जाती है। ROM (रीड-ओनली मेमोरी / स्टोरेज) स्थाई मेमोरी है जिसमें ऑपरेटिंग सिस्टम, ऐप्स, फोटो और वीडियो सुरक्षित रहते हैं। अधिक RAM से फोन हैंग नहीं होता और अधिक स्टोरेज से ज्यादा फाइलें सुरक्षित रखी जा सकती हैं।",
            category = QuestionCategory.TECHNOLOGY,
            difficulty = "Easy",
            keyTakeawayEn = "RAM = fast temporary working memory; ROM/Storage = permanent data storage.",
            keyTakeawayHi = "RAM अस्थाई कार्यशील मेमोरी है, जबकि ROM/स्टोरेज स्थाई डेटा सहेजने का स्थान है।",
            funFactEn = "The computer that guided Apollo 11 to the Moon in 1969 had just 4 Kilobytes of RAM!",
            funFactHi = "1969 में चंद्रमा पर अपोलो 11 को ले जाने वाले कंप्यूटर में सिर्फ 4 किलोबाइट (KB) RAM थी!",
            tags = listOf("hardware", "ram", "rom", "smartphones", "computers")
        ),
        QuestionItem(
            id = "tech_3",
            questionEn = "How does the Internet actually work?",
            questionHi = "इंटरनेट असल में कैसे काम करता है?",
            answerEn = "The Internet is a global network of interconnected computers communicating through standardized protocols (TCP/IP). When you visit a website, your request travels through Wi-Fi/cellular towers, through fiber-optic undersea cables that span ocean floors, hits Domain Name System (DNS) servers to look up the server IP address, and reaches a data center server that beams the webpage back to your screen in milliseconds.",
            answerHi = "इंटरनेट आपस में जुड़े करोड़ों कंप्यूटरों का एक वैश्विक जाल है जो TCP/IP प्रोटोकॉल के जरिए बात करते हैं। जब आप कोई वेबसाइट खोलते हैं, तो आपका अनुरोध वाई-फाई या मोबाइल नेटवर्क से होकर समुद्र के नीचे बिछी विशाल ऑप्टिकल फाइबर केबलों (Submarine Cables) के जरिए DNS सर्वर और मुख्य डेटा सेंटर तक पहुंचता है, और कुछ ही मिलीसेकंड में वेबसाइट आपके फोन पर लोड हो जाती है।",
            category = QuestionCategory.TECHNOLOGY,
            difficulty = "Medium",
            keyTakeawayEn = "Over 95% of international internet traffic travels through underwater fiber-optic cables, not satellites.",
            keyTakeawayHi = "दुनिया का 95% से अधिक इंटरनेट ट्रैफिक उपग्रहों से नहीं, बल्कि समुद्र के नीचे बिछी केबलों से गुजरता है।",
            funFactEn = "There are over 500 subsea fiber optic cables spanning over 1.4 million kilometers underwater!",
            funFactHi = "दुनिया के समुद्रों के नीचे 500 से अधिक ऑप्टिकल फाइबर केबल बिछी हैं जिनकी लंबाई 14 लाख किमी से अधिक है!",
            tags = listOf("internet", "networking", "cables", "dns", "technology")
        ),

        // ================= HISTORY =================
        QuestionItem(
            id = "hist_1",
            questionEn = "Who built the Taj Mahal and why was it constructed?",
            questionHi = "ताजमहल का निर्माण किसने और क्यों करवाया था?",
            answerEn = "The Taj Mahal in Agra, India was commissioned in 1632 by the Mughal Emperor Shah Jahan as a mausoleum for his beloved wife, Mumtaz Mahal. Built entirely of ivory-white Makrana marble on the southern bank of the Yamuna River, it required over 20,000 artisans and took around 22 years to complete. It is designated as a UNESCO World Heritage site and one of the New Seven Wonders of the World.",
            answerHi = "आगरा स्थित ताजमहल का निर्माण मुगल बादशाह शाहजहाँ ने अपनी बेगम मुमताज महल की याद में 1632 में शुरू करवाया था। यमुना नदी के तट पर सफेद मकराना संगमरमर से बने इस भव्य मकबरे को बनने में लगभग 22 वर्ष लगे और 20,000 से अधिक कारीगरों ने काम किया। यह यूनेस्को विश्व धरोहर स्थल और दुनिया के सात अजूबों में शामिल है।",
            category = QuestionCategory.HISTORY,
            difficulty = "Easy",
            keyTakeawayEn = "Built by Shah Jahan in memory of Mumtaz Mahal between 1632–1653 using white Makrana marble.",
            keyTakeawayHi = "शाहजहाँ ने मुमताज की स्मृति में 1632 से 1653 के बीच मकराना संगमरमर से ताजमहल बनवाया।",
            funFactEn = "The four minarets around the Taj Mahal were built leaning slightly outward so they would fall away from the main dome during an earthquake!",
            funFactHi = "ताजमहल की चारों मीनारें हल्की सी बाहर की तरफ झुकी हैं ताकि भूकंप आने पर वे मुख्य गुंबद पर न गिरें!",
            tags = listOf("history", "taj mahal", "mughal", "india", "monuments")
        ),
        QuestionItem(
            id = "hist_2",
            questionEn = "What was the main cause of the First World War (1914–1918)?",
            questionHi = "प्रथम विश्व युद्ध (1914–1918) का मुख्य कारण क्या था?",
            answerEn = "The immediate trigger was the assassination of Archduke Franz Ferdinand (heir to the Austro-Hungarian throne) and his wife Sophie by Gavrilo Princip in Sarajevo on June 28, 1914. This sparked a diplomatic crisis that ignited long-standing tensions summarized by the acronym M-A-I-N: Militarism, Alliances, Imperialism, and Nationalism among European powers.",
            answerHi = "प्रथम विश्व युद्ध का तात्कालिक कारण 28 जून 1914 को सारायेवो में ऑस्ट्रिया-हंगरी के राजकुमार आर्कड्यूक फ्रांज फर्डिनेंड की हत्या थी। इसके बाद यूरोपीय देशों की गुप्त संधियां सक्रिय हो गईं। इसके चार मुख्य अंतर्निहित कारण थे: सैन्यवाद (Militarism), गुटबंदी (Alliances), साम्राज्यवाद (Imperialism) और उग्र राष्ट्रवाद (Nationalism)।",
            category = QuestionCategory.HISTORY,
            difficulty = "Medium",
            keyTakeawayEn = "Assassination of Archduke Franz Ferdinand triggered the alliance network across Europe.",
            keyTakeawayHi = "आर्कड्यूक फ्रांज फर्डिनेंड की हत्या ने यूरोपीय देशों के गठबंधन को युद्ध में धकेल दिया।",
            funFactEn = "Over 30 nations declared war between 1914 and 1918, leading to over 20 million deaths.",
            funFactHi = "इस युद्ध में 30 से अधिक देशों ने भाग लिया था और 2 करोड़ से अधिक लोगों की जान गई थी।",
            tags = listOf("history", "world war 1", "europe", "franz ferdinand")
        ),

        // ================= EXAM PREPARATION =================
        QuestionItem(
            id = "exam_1",
            questionEn = "Who is known as the Father of the Indian Constitution?",
            questionHi = "भारतीय संविधान का जनक (Father of Indian Constitution) किसे कहा जाता है?",
            answerEn = "Dr. Bhimrao Ramji Ambedkar (B. R. Ambedkar) is revered as the Father and Chief Architect of the Constitution of India. As the Chairman of the Drafting Committee of the Constituent Assembly, he led the preparation of the world's longest written national constitution, guaranteeing fundamental rights, secularism, and equality for all citizens.",
            answerHi = "डॉ. भीमराव रामजी आंबेडकर को भारतीय संविधान का जनक और मुख्य वास्तुकार कहा जाता है। वे संविधान सभा की प्रारूप समिति (Drafting Committee) के अध्यक्ष थे। उनके नेतृत्व में 2 वर्ष, 11 माह और 18 दिन में दुनिया का सबसे बड़ा लिखित संविधान तैयार हुआ, जिसे 26 जनवरी 1950 को लागू किया गया।",
            category = QuestionCategory.EXAM_PREP,
            difficulty = "Easy",
            keyTakeawayEn = "Dr. B. R. Ambedkar chaired the Drafting Committee that framed India's Constitution.",
            keyTakeawayHi = "डॉ. बी. आर. आंबेडकर संविधान सभा की प्रारूप समिति के अध्यक्ष थे।",
            funFactEn = "The original Indian Constitution was handwritten in English and Hindi by calligrapher Prem Behari Narain Raizada, not printed!",
            funFactHi = "भारत का मूल संविधान किसी मशीन से नहीं, बल्कि प्रेम बिहारी नारायण रायजादा ने अपने हाथों से लिखा था!",
            tags = listOf("constitution", "ambedkar", "polity", "upsc", "ssc")
        ),
        QuestionItem(
            id = "exam_2",
            questionEn = "Which layer of Earth's atmosphere protects us from harmful ultraviolet (UV) rays?",
            questionHi = "वायुमंडल की कौन सी परत हमें हानिकारक पराबैंगनी (UV) किरणों से बचाती है?",
            answerEn = "The Ozone Layer, located in the Stratosphere (between roughly 15 to 35 km above Earth's surface), absorbs 97% to 99% of the sun's medium-frequency ultraviolet light (UV-B), protecting living organisms from skin cancer, cataracts, and genetic damage.",
            answerHi = "समताप मंडल (Stratosphere) में स्थित 'ओजोन परत' (Ozone Layer) सूर्य की हानिकारक पराबैंगनी (UV) किरणों को सोख लेती है। यह लगभग 15 से 35 किमी की ऊंचाई पर स्थित है और पृथ्वी पर त्वचा कैंसर और अन्य बीमारियों से जीवन की रक्षा करती है।",
            category = QuestionCategory.EXAM_PREP,
            difficulty = "Medium",
            keyTakeawayEn = "Ozone layer in the stratosphere absorbs lethal solar UV radiation.",
            keyTakeawayHi = "समताप मंडल की ओजोन परत हानिकारक पराबैंगनी विकिरण को पृथ्वी तक आने से रोकती है।",
            funFactEn = "The Montreal Protocol (1987) banning CFCs is the most successful international environmental treaty in history!",
            funFactHi = "1987 का मॉन्ट्रियल प्रोटोकॉल दुनिया का सबसे सफल पर्यावरण समझौता है जिसने ओजोन छिद्र को भरने में मदद की!",
            tags = listOf("geography", "atmosphere", "ozone", "stratosphere", "environment")
        ),

        // ================= HEALTH & BIOLOGY =================
        QuestionItem(
            id = "hlth_1",
            questionEn = "Why is 7 to 8 hours of sleep essential for human brain and health?",
            questionHi = "मानव मस्तिष्क और स्वास्थ्य के लिए 7 से 8 घंटे की नींद क्यों जरूरी है?",
            answerEn = "During deep sleep, the brain activates the 'glymphatic system', a specialized waste clearance mechanism that flushes out toxic proteins like beta-amyloid (linked to Alzheimer's disease). Sleep is also critical for memory consolidation, tissue repair, immune cell production, hormonal regulation, and emotional balance.",
            answerHi = "गहरी नींद के दौरान मस्तिष्क का 'ग्लाइम्फैटिक सिस्टम' (Glymphatic System) सक्रिय होता है, जो दिनभर में जमा हुए विषैले तत्वों (जैसे बीटा-एमाइलॉयड प्रोटीन) को साफ करता है। नींद याददाश्त मजबूत करने, कोशिकाओं की मरम्मत, रोग प्रतिरोधक क्षमता बढ़ाने और मानसिक तनाव कम करने के लिए अत्यंत आवश्यक है।",
            category = QuestionCategory.HEALTH,
            difficulty = "Easy",
            keyTakeawayEn = "Sleep flushes brain toxins via the glymphatic system and consolidates memories.",
            keyTakeawayHi = "नींद के दौरान मस्तिष्क विषैले पदार्थों को साफ करता है और याददाश्त को पक्का करता है।",
            funFactEn = "Humans spend roughly one-third of their entire lives sleeping — about 25 to 30 years!",
            funFactHi = "इंसान अपनी पूरी जिंदगी का लगभग एक-तिहाई हिस्सा (25 से 30 साल) सिर्फ सोने में बिताता है!",
            tags = listOf("health", "sleep", "brain", "wellness", "biology")
        ),
        QuestionItem(
            id = "hlth_2",
            questionEn = "What is normal human blood pressure and what do the numbers mean?",
            questionHi = "सामान्य मानव रक्तचाप (Blood Pressure) कितना होता है और दोनों संख्याओं का क्या मतलब है?",
            answerEn = "A normal resting blood pressure for a healthy adult is approximately 120/80 mm Hg. The top number (120) is 'Systolic Pressure' — the pressure exerted when the heart contracts and pumps blood. The bottom number (80) is 'Diastolic Pressure' — the pressure in arteries when the heart rests between beats.",
            answerHi = "एक स्वस्थ वयस्क का सामान्य रक्तचाप लगभग 120/80 mm Hg होता है। ऊपर की संख्या (120) को 'सिस्टोलिक' (Systolic) कहते हैं, जो दिल के धड़कने पर नसों में रक्त के दबाव को दर्शाती है। नीचे की संख्या (80) को 'डायस्टोलिक' (Diastolic) कहते हैं, जो दो धड़कनों के बीच दिल के आराम की स्थिति में दबाव दर्शाती है।",
            category = QuestionCategory.HEALTH,
            difficulty = "Easy",
            keyTakeawayEn = "Normal BP is 120/80 mmHg (Systolic / Diastolic).",
            keyTakeawayHi = "सामान्य बीपी 120/80 mmHg होता है (सिस्टोलिक दबाव / डायस्टोलिक दबाव)।",
            funFactEn = "A giraffe's blood pressure is about twice that of humans (around 280/180) to pump blood up its long neck!",
            funFactHi = "जिराफ का रक्तचाप इंसानों से दोगुना (280/180) होता है ताकि खून उसकी 6 फीट लंबी गर्दन तक चढ़ सके!",
            tags = listOf("health", "heart", "blood pressure", "biology")
        )
    )

    fun search(query: String, category: QuestionCategory = QuestionCategory.ALL): List<QuestionItem> {
        val trimmed = query.trim().lowercase()
        return allQuestions.filter { item ->
            val matchesCategory = category == QuestionCategory.ALL || item.category == category
            if (!matchesCategory) return@filter false

            if (trimmed.isEmpty()) return@filter true

            val inEn = item.questionEn.lowercase().contains(trimmed) ||
                    item.answerEn.lowercase().contains(trimmed) ||
                    item.keyTakeawayEn.lowercase().contains(trimmed)

            val inHi = item.questionHi.lowercase().contains(trimmed) ||
                    item.answerHi.lowercase().contains(trimmed) ||
                    item.keyTakeawayHi.lowercase().contains(trimmed)

            val inTags = item.tags.any { it.lowercase().contains(trimmed) }

            inEn || inHi || inTags
        }
    }

    fun getQuestionOfDay(): QuestionItem {
        val dayOfYear = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_YEAR)
        val index = dayOfYear % allQuestions.size
        return allQuestions[index]
    }
}
