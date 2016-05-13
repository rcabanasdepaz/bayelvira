// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
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

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x5_1 x5_2 x5_3);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
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
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
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

link D1 X2;

link D1 X3;

link D1 X4;

link D1 X6;

link D2 V0;

link D2 X2;

link X2 X1;

link X3 X2;

link X4 D2;

link X4 X6;

link X5 D2;

link X5 X4;

link X6 D2;

link X6 X1;

link X7 D1;

link X7 X4;

link X8 D1;

link X8 X7;

link X9 D1;

link X9 X7;

//Network Relationships: 

relation X7 X8 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.13589157860481052 0.10059707483457156 0.7191496139097955 0.5431775782414848 0.8641084213951895 0.8994029251654285 0.28085038609020463 0.4568224217585151 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4673137498799417 0.5326862501200584 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.36646536179020556 0.6335346382097944 );
}

relation X4 D1 X5 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.689633016814631 0.65620925858218 0.8711094735482334 0.06963136688850201 0.3723501572890286 0.39199549792688676 0.2616309210640163 0.4093635149776249 0.3259948822552401 0.7537865358557028 0.383649914666114 0.6737146923006208 0.3203511806356839 0.47500656603576996 0.919475887583391 0.058740580668589226 0.9141826383199005 0.75368869965425 0.310366983185369 0.34379074141781996 0.12889052645176663 0.9303686331114981 0.6276498427109715 0.6080045020731133 0.7383690789359836 0.590636485022375 0.6740051177447599 0.2462134641442972 0.616350085333886 0.32628530769937913 0.6796488193643161 0.52499343396423 0.08052411241660887 0.9412594193314108 0.08581736168009944 0.24631130034575002 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2836888678821982 0.340483367746009 0.37582776437179266 );
}

relation X6 D1 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.10530974421451895 0.18319824727021833 0.6400504259167705 0.48332225788996636 0.8355856531060963 0.3132733031430925 0.894690255785481 0.8168017527297816 0.3599495740832295 0.5166777421100337 0.16441434689390358 0.6867266968569076 );
}

relation X1 X2 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.09510850918389611 0.1477503302863444 0.18881363119721642 0.30326110587992144 0.5733153557190355 0.5640164303639429 0.3547635123758029 0.3734587878503789 0.33157613509706846 0.2882332393497127 0.45642285642698077 0.3232801062696997 );
}

relation X2 D1 D2 X3 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4812192826544068 0.6829554336924085 0.37702756959204703 0.6744372671461913 0.5338425315470574 0.16205294894691433 0.9577668328393192 0.5653324059341198 0.6419245814144486 0.4826761253089135 0.5037286193521032 0.546461663556501 0.5187807173455933 0.3170445663075915 0.6229724304079529 0.3255627328538086 0.46615746845294265 0.8379470510530856 0.04223316716068072 0.4346675940658803 0.35807541858555136 0.5173238746910864 0.4962713806478967 0.4535383364434989 );
}

relation X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.42257522675858245 0.28026275968432685 0.16341169316132262 0.5774247732414176 0.7197372403156732 0.8365883068386774 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (110.48154653535961 98.25345628326195 140.95910159820625 128.73101134610857 131.5821764869504 119.35408623485273 );
}

}
