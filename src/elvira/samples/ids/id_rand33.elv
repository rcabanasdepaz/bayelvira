// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x9_1 x9_2 x9_3);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x11_1 x11_2 x11_3);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x13_1 x13_2);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (d1_1 d1_2 d1_3);
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
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
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
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x1_1 x1_2 x1_3);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
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
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x4_1 x4_2 x4_3);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =5;
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

link D1 X5;

link D1 X6;

link D2 V0;

link D2 X3;

link X1 V0;

link X1 X7;

link X10 D1;

link X10 X1;

link X11 D1;

link X11 X4;

link X12 D1;

link X12 X10;

link X12 X13;

link X12 X2;

link X12 X9;

link X13 D1;

link X13 X11;

link X13 X3;

link X4 V0;

link X4 X2;

link X5 D2;

link X5 X4;

link X6 D2;

link X6 X4;

link X7 D2;

link X7 X5;

link X8 D2;

link X8 X5;

link X8 X7;

link X9 D1;

link X9 X1;

//Network Relationships: 

relation X9 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.4342581435491471 0.08888462115732938 0.5540173705887677 0.9111153788426707 0.011724485862085174 );
}

relation X10 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7501414803815384 0.02622258071078106 0.24985851961846167 0.9737774192892189 );
}

relation X11 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9491776974371321 0.45451943580880977 0.05082230256286797 0.3994777092655459 0.0 0.14600285492564433 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 );
}

relation X13 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.16937761854150638 0.9856803971732803 0.8306223814584935 0.014319602826719822 );
}

relation X5 X7 X8 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5895264146760258 0.7406837591265629 0.6832547818614222 1.0 0.4245611800386696 0.0 0.5249235114614168 0.6514246251867817 0.6373546313720065 0.0 0.315129524162606 0.8829900166402157 0.41047358532397415 0.25931624087343697 0.31674521813857776 0.0 0.5754388199613304 0.0 0.4750764885385832 0.34857537481321826 0.3626453686279934 1.0 0.6848704758373941 0.11700998335978424 );
}

relation X6 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9833037665845435 0.2170378287233746 0.0 0.016696233415456514 0.7829621712766254 1.0 );
}

relation X7 X8 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.0 0.0721216412010416 1.0 0.0 1.0 0.0 0.0 0.9278783587989584 0.0 1.0 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 );
}

relation X1 X10 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.09309307165636414 0.6962100505681491 0.36208079865342613 1.0 0.3103315908832652 0.36288346190863624 0.1597717490388907 0.2161919539912148 1.0 0.9534822401629616 0.330229630307571 0.2758640613349731 0.4085360093957268 0.3293410179719433 0.36283091279509166 0.0 0.29241639216831034 0.9643360455151866 0.6848563265370506 0.0 0.1391138172976886 0.0 0.4980015810166349 0.0 0.10243838830793867 0.2987119410599858 0.0 0.019863866298854217 0.3278114377819066 0.22125467387082767 0.5870783188940772 0.21974017105509513 0.16330458442217977 0.2762431780858061 0.7075836078316897 0.03566395448481333 0.22205060180658537 0.30378994943185084 0.49880538404888536 0.0 0.19166682810009986 0.6371165380913637 0.7377898626531707 0.48509610494879946 0.0 0.02665389353818412 0.34195893191052246 0.5028812647941994 0.00438567171019606 0.45091881097296155 0.4738645027827286 0.7237568219141939 );
}

relation X2 X4 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.8945480966267636 0.9453631277452605 1.0 0.41415526208797426 1.0 1.0 0.10545190337323632 0.05463687225473941 0.0 0.5858447379120257 0.0 );
}

relation X3 X13 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.07288505227117369 0.7409870583277008 0.0 0.29526626727319977 0.9271149477288263 0.25901294167229916 1.0 0.7047337327268002 );
}

relation X4 X5 X6 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5794316911302457 0.0 0.6569446548431838 0.2676422993754972 0.0 0.778616517322155 0.0 0.0 0.0 1.0 0.17272774595431306 0.0 0.0 0.2909536842586535 0.051718498322034574 0.3454219189641228 0.0 0.0 0.9300138114713771 0.0 0.24450214219024544 0.0 0.035365730881536946 1.0 0.42056830886975427 0.7090463157413465 0.2913368468347816 0.38693578166038 1.0 0.22138348267784497 0.06998618852862291 1.0 0.7554978578097544 0.0 0.79190652316415 0.0 );
}

relation V0 D1 D2 X1 X4 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (160.19182106034148 165.68235512203157 153.94563604296732 173.9898604473467 179.48039450903678 0.0 188.80035804163046 0.0 0.0 160.4553267443147 165.9458608060048 154.20914172694054 174.25336613131992 179.74390019301 168.00718111394576 0.0 194.55439778729377 0.0 176.1326366891258 181.6231707508159 0.0 0.0 195.4212101378211 183.68449105875686 204.74117367041478 0.0 198.49498865304062 0.0 181.8866764347891 170.14995735572487 190.19418176010424 195.68471582179433 183.94799674273008 0.0 210.4952134160781 198.75849433701384 204.16459416805898 0.0 0.0 217.9626335550642 0.0 211.71644853769004 232.77313114934796 238.26366521103805 226.5269461319738 204.4280998520322 0.0 198.18191483465804 0.0 0.0 211.97995422166326 0.0 0.0 226.79045181594702 );
}

}
