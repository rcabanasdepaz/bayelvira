// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x14_1 x14_2);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (d1_1 d1_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node D2(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =5;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (d2_1 d2_2);
}

node X1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x1_1 x1_2);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =3;
pos_y =5;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

// Links of the associated graph:

link D1 D2;

link D1 V0;

link D1 X1;

link D1 X10;

link D1 X3;

link D1 X7;

link D1 X8;

link D2 V0;

link D2 X3;

link D2 X4;

link X1 X10;

link X10 D2;

link X10 X9;

link X11 D1;

link X11 X13;

link X12 D1;

link X12 X11;

link X13 D1;

link X13 X14;

link X14 D1;

link X14 X10;

link X2 X13;

link X2 X3;

link X2 X4;

link X5 D2;

link X5 X7;

link X5 X9;

link X6 D2;

link X6 X11;

link X7 D2;

link X7 X10;

link X8 D2;

link X8 X1;

link X8 X5;

link X9 D2;

link X9 X3;

//Network Relationships: 

relation X11 X12 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7966299574164244 0.8736265074502102 0.09076592693913181 0.6488493748132479 0.2033700425835756 0.1263734925497898 0.9092340730608682 0.3511506251867521 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.09970023670893162 0.9002997632910684 );
}

relation X13 X11 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4290125733281623 0.7962749335079463 0.02045781949743896 0.6713013867443157 0.5709874266718378 0.20372506649205363 0.979542180502561 0.32869861325568434 );
}

relation X14 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.31291118086685804 0.5715162895005665 0.687088819133142 0.4284837104994334 );
}

relation X5 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2662226225878187 0.5735612490092458 0.7337773774121813 0.4264387509907542 );
}

relation X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5257425492806599 0.4742574507193401 );
}

relation X7 D1 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.48020774298111857 0.6822819574742714 0.9042470379214461 0.3267141532676837 0.5197922570188814 0.3177180425257286 0.09575296207855401 0.6732858467323163 );
}

relation X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.07196803687526934 0.32032086219312994 0.9280319631247306 0.6796791378068702 );
}

relation X9 X10 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7315107875505429 0.9282507225783164 0.7987768250373969 0.41708812389718836 0.2684892124494571 0.07174927742168347 0.20122317496260303 0.5829118761028117 );
}

relation X10 D1 X1 X14 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.37709124588417514 0.1538408911926672 0.040473369401468395 0.2706860489271034 0.5655066491406301 0.47842283727005724 0.40526906306165383 0.6778500122810712 0.3046472146540554 0.3822571695426802 0.04583483237475996 0.3194520289500375 0.9991432004815528 0.29464997497359835 0.45745436895789243 0.6849480240596959 0.6229087541158248 0.8461591088073329 0.9595266305985316 0.7293139510728965 0.43449335085936996 0.5215771627299428 0.5947309369383462 0.3221499877189288 0.6953527853459446 0.6177428304573199 0.95416516762524 0.6805479710499626 8.567995184471572E-4 0.7053500250264017 0.5425456310421076 0.315051975940304 );
}

relation X1 D1 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.326812189252883 0.1051657350866797 0.47128432279665844 0.11370088784934815 0.673187810747117 0.8948342649133203 0.5287156772033414 0.8862991121506519 );
}

relation X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.21988363378148182 0.7801163662185182 );
}

relation X3 D1 D2 X2 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.11702466160026043 0.22269994418479924 0.3475581015562514 0.8689852647141701 0.5011559229244371 0.7613064619826104 0.11275761550483651 0.9830837590345822 0.5627420479623961 0.01394325151827681 0.11344588603930648 0.628829518376608 0.4259981474673903 0.2563204160645656 0.5487615183972137 0.07215638810872033 0.8829753383997395 0.7773000558152008 0.6524418984437487 0.1310147352858299 0.49884407707556283 0.23869353801738963 0.8872423844951636 0.016916240965417804 0.4372579520376039 0.9860567484817233 0.8865541139606935 0.37117048162339195 0.5740018525326097 0.7436795839354342 0.4512384816027864 0.9278436118912797 );
}

relation X4 D2 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.32063768337129145 0.6152024768810422 0.1035992395042464 0.5201361746441795 0.6793623166287085 0.3847975231189578 0.8964007604957535 0.47986382535582045 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (150.41706200977592 110.48154653535961 180.89461707262254 140.95910159820625 );
}

}
