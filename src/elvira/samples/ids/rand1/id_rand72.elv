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
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x9_1 x9_2 x9_3);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x10_1 x10_2 x10_3);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x12_1 x12_2 x12_3);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x13_1 x13_2 x13_3);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x14_1 x14_2);
}

node X15(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (x15_1 x15_2 x15_3 x15_4);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (d1_1 d1_2 d1_3 d1_4);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x5_1 x5_2 x5_3);
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
num-states = 4;
states = (x7_1 x7_2 x7_3 x7_4);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x8_1 x8_2 x8_3);
}

node D2(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =5;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (d2_1 d2_2 d2_3 d2_4);
}

node X1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (x1_1 x1_2 x1_3 x1_4);
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
num-states = 3;
states = (x3_1 x3_2 x3_3);
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

link D1 X2;

link D1 X3;

link D1 X4;

link D1 X7;

link D2 V0;

link D2 X1;

link D2 X2;

link D2 X3;

link X10 D1;

link X10 X1;

link X10 X13;

link X11 D1;

link X11 X10;

link X12 D1;

link X12 X3;

link X12 X9;

link X13 D1;

link X13 X14;

link X14 D1;

link X14 X7;

link X15 D1;

link X15 X10;

link X15 X13;

link X3 X2;

link X3 X4;

link X4 X1;

link X5 D2;

link X5 X14;

link X6 D2;

link X6 X13;

link X7 D2;

link X8 D2;

link X8 X6;

link X9 D1;

link X9 X10;

link X9 X14;

link X9 X3;

//Network Relationships: 

relation X9 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5897740150356617 0.18513046995627233 0.24924080531340964 0.3997015570080817 0.11268290567405903 0.38971501945488213 0.010524427956256554 0.7021866243696686 0.36104417523170823 );
}

relation X10 X11 X15 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.25152689118679566 0.0954399706649898 0.34893901230512286 0.07933432275991761 0.030752410421124143 0.4302535702109228 0.48419186545149745 0.16691391589754215 0.5008654831088366 0.6814600554888927 0.41744497927570995 0.07352327340692066 0.26725638853953126 0.5137182652807476 0.3354153870251135 0.3140631098364118 0.39863913154742747 0.3037473254845146 0.2759454399854961 0.09035601332399257 0.2794076583071143 0.06544207071721125 0.6020142213889345 0.17935658174735336 0.3860431576149315 0.5801837824330288 0.19945324385753818 0.7193484145388561 0.9342227901118833 0.12689260196879074 0.35524260267337654 0.4578713725595676 0.079966333267829 0.18863092228969153 0.3846711638212033 0.6787363993392487 0.3822433985659004 0.3726822080519717 0.656415192752136 0.35920820015462784 0.3532449509816759 0.5121453978038232 0.4135843092255524 0.2826192374277568 0.35801595166963723 0.8211705701642295 0.213045159968071 0.481851630947725 0.3624299511982729 0.32437624690198136 0.4516077438373391 0.20131726270122619 0.03502479946699261 0.4428538278202865 0.16056553187512604 0.3752147115428902 0.41916818362333436 0.12990902222141593 0.19788385690308677 0.24774032725383052 0.3505002128945683 0.11359952666728068 0.00816942022275055 0.3267286900089604 0.24811591747089662 0.1841072767116622 0.31047025078895163 0.6270247492482506 0.36257639002324843 0.1133873591185594 0.18494061864299466 0.33879178730492165 );
}

relation X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.028852053905869292 0.9711479460941307 );
}

relation X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2438981186148877 0.2254315024980331 0.5306703788870792 );
}

relation X13 X10 X15 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3432672963337183 0.37627726389268773 0.5414438513363327 0.38395090668033244 0.30225320268840455 0.2943759877570454 0.4502925497891538 0.09975017833642749 0.8531075913195358 0.494961656992743 0.16479532266157132 0.31989114571976013 0.3679684399083329 0.252084108308731 0.3410874332252585 0.43033916881895207 0.6640335611889175 0.28229169818228794 0.2430865451350319 0.3203496121751137 0.0505763063230523 0.3108269212323924 0.5858021324792181 0.4596715345380331 0.3269374245329504 0.529987202877118 0.37455120878778014 0.2186642912150533 0.31374541081597057 0.07246329919168948 0.08450267445468618 0.24298147413938664 0.04462054240565278 0.23673626952619212 0.2638567488195365 0.4304448241082288 0.0011487652830733898 0.6154274197435892 0.21967179244133572 0.1640435544154911 0.03208305006607209 0.5165146171947376 0.6125337210719993 0.34910059102967 0.5436051162882167 0.6703240286383347 0.09143359172724498 0.43717674672803486 0.3297952791333312 0.09373553323019435 0.08400493987588727 0.39738480210461424 0.3840013864956249 0.6331607130512651 0.46520477575616004 0.657268347524186 0.10227186627481151 0.26830207348106494 0.5713479285188922 0.2496640301720111 0.6308827948085937 0.13248847194767982 0.43924077433340586 0.4056172767655569 0.30388338874501036 0.20119368462297454 0.1443797337929687 0.33054979679521634 0.405818577388731 0.01884905012927281 0.322764275793537 0.10315171873393203 );
}

relation X14 X13 X5 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3279062173659533 0.5988542737379361 0.5953431475584878 0.4948405280140674 0.6012125588689778 0.740992905885592 0.9000445754057695 0.30585766567278694 0.3048338370631901 0.833521134068525 0.49302912791974846 0.57947647400902 0.6800740112510052 0.4421437002787825 0.670888446837733 0.680480425979502 0.29746175410381054 0.5194138233511385 0.265008124536767 0.5694169997185194 0.2833654320632306 0.45365620629240544 0.6526881944458202 0.18149160535369405 0.4334813086177119 0.15209228079906625 0.6436047011952061 0.6720937826340467 0.40114572626206396 0.4046568524415122 0.5051594719859327 0.3987874411310222 0.2590070941144081 0.09995542459423043 0.6941423343272131 0.6951661629368099 0.16647886593147493 0.5069708720802516 0.42052352599098003 0.3199259887489948 0.5578562997212174 0.32911155316226703 0.319519574020498 0.7025382458961895 0.4805861766488615 0.734991875463233 0.43058300028148055 0.7166345679367694 0.5463437937075946 0.3473118055541798 0.8185083946463059 0.5665186913822882 0.8479077192009338 0.3563952988047939 );
}

relation X15 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.4977838021279308 0.16292850881759224 0.19953515271695196 0.1397525363375251 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.41751224603486214 0.43144060386980326 0.15104715009533468 );
}

relation X6 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5998222540522932 0.04536844902436835 0.24094730048351212 0.4001777459477069 0.9546315509756316 0.7590526995164879 );
}

relation X7 D1 X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.24922872793307696 0.19350358469474543 0.3313761852913978 0.2078529893262833 0.14745331634638714 0.0980769582107809 0.06650263163309997 0.14826581727407578 0.5641933194546513 0.31680119307271637 0.2049844136567539 0.18567525639671356 0.20429407678718806 0.03258556974949443 0.32117034772031255 0.06572029010166579 0.035147550095263894 0.2701807267189545 0.22352427706941005 0.26495805390836824 0.24010389742643942 0.5329779264744372 0.33037221949920925 0.4047467168239907 0.151430402517008 0.21951449551358354 0.24011512398243817 0.3415137003686349 0.4081487094399854 0.33635954556528747 0.28195480114737825 0.3812671758002677 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.2658932892687693 0.5012779150928065 0.23282879563842412 );
}

relation X1 D1 D2 X10 X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.39453589184481735 0.30756275516107046 0.17199650016755946 0.0411125322066052 0.322166795133607 0.4786693313731027 0.22849143481959713 0.23953730250296432 0.4898488988990855 0.44650999595569657 0.2096964198470746 0.15509595082522654 0.1291974004681786 0.47246592957138134 0.2842275770826623 0.18408178180057005 0.07529453476293081 0.28922012599611024 0.35391974306430757 0.4014606851913327 0.297802320099993 0.0827995520637602 0.10750701124142602 0.2073100095925029 0.10228394913696104 0.20572302722280472 0.15930506837814953 0.23632284626718544 0.3333699649312452 0.14605242133512378 0.23611468749763698 0.3860741967949338 0.23654791421652677 0.17418027082548193 0.28812031492136975 0.3232352927020477 0.2478484942821999 0.12386086590305113 0.2851286440021056 0.41569281171957845 0.3060581364887192 0.426437146846453 0.19088718636113688 0.10360920772981576 0.3130297557398144 0.08725451923618044 0.3554885870933681 0.06713282347810842 0.40068610224775353 0.12498073127167636 0.20984072352663538 0.23452579512002136 0.0207734619587251 0.11545414302313713 0.16141776588580176 0.018721467773384366 0.054456768383989836 0.3348173035919653 0.2941878360857029 0.19802046279849556 0.2839299180967267 0.3964550663221752 0.5942474405035247 0.36305582768770983 0.461107032695363 0.32402980840309675 0.32452738510979695 0.13645722249070275 0.29955980211394034 0.15840589635378344 0.21113524049539317 0.11224401833749469 0.4559331316852969 0.06393433456984396 0.3800883862760474 0.12150062356776913 0.43227775273985897 0.3564312604797294 0.40098817904817047 0.06956612073128568 0.4330740893773508 0.34294683667797593 0.40695254810568865 0.17659880435188874 0.16434361760987257 0.3453247816571562 0.4753752592693387 0.10625137867057129 0.14506238837735344 0.16062552320569087 0.32898400492064617 0.1154851803671399 0.31157348245389765 0.29063893422947323 0.2864360505783166 0.07978775433372692 0.14781414541989474 0.34565926400578256 0.042885675612835655 0.34917718276935605 0.39942171458881276 0.1591722318617854 0.15648065786749757 0.1717073811331024 0.41009361958927626 0.11261902054260643 0.0841274390132426 0.2540901087111342 0.48998971184461587 0.11347197517927494 0.21494676187411474 0.03026162042587002 0.3997721457569759 0.26619205010766045 0.3877749028396879 0.3014060697205435 0.1360403012844765 0.08704767767663472 0.3100811970031222 0.21543667471031824 0.6571647333466778 0.08967493386221778 0.27627201812403257 0.11924676219376143 0.1581874395023406 0.4190612924549825 0.16286051979746732 0.17152848122788444 0.06719754597039868 0.5041425407180765 0.1676474920123498 0.3774760412924357 0.24112675472375875 0.17315813526239687 0.3741739898132767 0.022419170580597683 0.01154928681555769 0.33782127666744605 0.3134800916332127 0.07140343912032916 0.10089481121177243 0.2237002506342741 0.10896468027523014 0.2273097213451425 0.06604153863063053 0.2091224264264071 0.24787616313949162 0.6316823640232943 0.09970182219148783 0.05838091287606824 0.32051903931522957 0.33329165903332575 0.16758571268677602 0.11931950180349288 0.28625937357277936 0.4625619395168984 0.3514134854336683 0.035048604737154584 0.04588252983882376 0.31475965324230376 0.13186899722232234 0.32564046986837086 0.18878008467598312 0.2373628257409698 0.2545198397158796 0.6478255820730796 0.2763567876022923 0.44086559628755717 0.15850557128158996 0.3374318495520236 0.0906695556847686 0.15031068895881347 0.43907473558382254 0.2926533257823036 0.33229148930115765 0.3213822337156853 0.33526890010514176 0.38497302532211447 0.24873144943880796 0.5472539695128004 0.12375355536843963 0.2212148088812287 0.33578827807912914 0.20121705802492845 0.3155179149633083 0.1363149227862096 0.30760805489896687 0.4912229373104036 0.15588354847229402 0.24558250712501278 0.167102975402451 0.13990046392732647 0.2211425053086599 0.415618281934004 0.11321022471941479 0.3488491685782633 0.06250602574566043 0.19695065133719564 0.46720970596522393 0.3165565412545736 0.15326511733217651 0.26560672983193967 0.3375932964499338 0.1329575166229593 0.20556500720341472 0.3094824267246447 0.3542701994762176 0.14783074612331723 0.12190125033198432 0.2848617262699789 0.35029088493954547 0.10145657331354067 0.36362845120412407 0.29687855221204545 0.13483768716552444 0.32035866548902087 0.16958458133932616 0.3621174382825281 0.05378721710570864 0.4147521131826776 0.19371271032853188 0.19968804366988938 0.32437369521039705 0.4399709688662874 0.08037715876336592 0.18640331239879582 0.09800493327967778 0.2339688248540039 0.36567926311163096 0.17538714117377668 0.12619250448138655 0.3556448741014685 0.3133181939503282 0.08995952482955559 0.1237840351767856 0.3085367286166289 0.23571418938215202 0.09114420131408041 0.41397205805057663 0.31442976978457826 0.2429027318618249 0.4642206827477816 0.18939846796088022 0.17196502441717268 0.12039298439681409 0.4306947870123933 0.15118440743439654 0.3455218904378527 0.06987624055743576 0.5361170650105233 0.00685383355364548 0.19420431895828102 0.18299031540389635 0.1977521004334333 0.1691718839445386 0.5137772019866806 0.29164003291636187 0.33414406744667524 0.10845882872321524 0.2752326132494125 0.2501892060122769 0.34567735006860323 0.1668932695719741 0.29779746539882285 0.049405251419293944 0.7289132304218013 0.2886183035816685 0.07370098803868737 0.11972661014180276 0.21745993782956585 0.542161295823807 0.0506993733205144 0.31165523585451316 0.040235777302867905 0.2810449409032429 0.15357849566422843 0.06278171013585046 0.25664787775920456 0.11834563971846908 0.007718456712514409 0.5940750673434637 0.5008436793924103 0.36846003916707887 0.2922755303137463 0.4755945480484748 0.4960539549719058 0.1954275266196529 0.2574808627366182 0.4220046710035963 0.06781553455135722 0.22705375732788088 0.32663393012316727 0.41469512834925826 0.23141146623118952 0.30394118389631236 0.09745913292977945 0.030741391946346074 0.0811890708058895 0.41000959883891225 0.0773034113436239 0.3200810570544592 0.4066908593051582 0.2909936998702147 0.4253314810837305 0.33378851255774133 0.07158119318068341 0.22681158361879603 0.2455999970369711 0.04105003454257482 0.144207446537575 0.5397983125528614 0.2747930952251696 0.3690536566267907 0.22511822223755368 0.2798109900751787 0.36710849676894397 0.11357414178284989 0.28215500922166437 0.24346978446904005 0.13954000370386516 0.29894649673596874 0.04749920364641152 0.2574733057869385 0.09403497635515913 0.5170843745555388 0.5606663352435051 0.36013401452203775 0.34122823738283975 0.3469899063420726 0.04757297338208843 0.274465003767109 0.10775598256541086 0.29194611987992763 0.08911566959705673 0.2356980996095766 0.6190443068431793 0.19356785576730973 0.03178262279537184 0.2803508737227962 0.04152294497451561 0.8972174353146066 0.4250256872536651 0.15063659899754128 0.45890162580027427 0.4228579667028686 0.12227465727952719 0.1687838905400331 0.34223132640997933 0.5046848333686552 0.13094744989478485 0.002346272530185823 0.4752377275688889 0.1317860478387072 0.24905772816117105 0.20240286694677548 0.06235951547196704 0.24198104750786487 0.2678570467172082 0.42203060784488844 0.23300103047197632 0.2712738068073411 0.08840158696737214 0.1980236825170703 0.30334060739611873 0.04505404201229937 0.15443821933620844 0.06690374996868421 0.30970916020216654 0.003359383220236804 0.35876773048967897 0.37671960460128184 0.1999239490828105 0.4509863711808848 0.19716719965484103 0.1956186835104936 0.3780414306994582 0.17276082239940513 0.1304377206419544 0.5121318731739791 0.2812814484665592 0.2732612675728549 0.32007580631171373 0.13678685543621902 0.3018752073991913 0.19521958067913003 0.07521251645933073 0.3520577402497426 0.05728519558608999 0.17540927616015053 0.09494126876662398 0.35491291811717773 0.012717415992937115 0.16406081324713856 0.30452984400370664 0.3452191324075976 0.22257341223900046 0.08039813646028816 0.3712912190511531 0.31581711664408596 0.10067864282601048 0.13372810027059112 0.3104264210602982 0.1590323384963616 0.09652129817199419 0.07145455131035412 0.2892032507515638 0.336062930218671 0.34448319069384664 0.15685690585950696 0.055786700172387835 0.26814290221636794 0.28644070222484824 0.14823755140916137 0.20196957102963908 0.05804433561831097 0.5717497241629796 0.34236901608291354 0.1563108051297488 0.36633348564782003 0.18719451383524363 0.3644235597383684 0.38735025087506597 0.485134006505984 0.62064523487358 0.2511744520168073 0.04430486677382834 0.23342087644111495 0.08890634959031257 0.17204350830532103 0.11117014855631713 0.4748011050734359 0.056504998791736386 0.11741789852095577 0.4154992838611652 0.353073579365068 5.711736469333468E-4 0.16343625432564685 0.2315382895196395 0.14704834196886096 0.3297119055339305 0.31182399646779246 0.35298143143492106 0.09403460774123368 0.3050386983142513 0.19930805704369395 0.08579460638630576 0.3788961932082992 0.07582708387339582 0.45904795545700555 0.3135582109548353 0.2304885886409134 0.11557649203072642 0.40662780564155254 0.12516741932558517 0.0763378886034203 0.07703129889477046 0.37015682295465824 0.23172690098761803 0.47021438586190323 0.246257968005057 0.11398115770208242 0.2791392436609083 0.1881240387321653 0.1530035657855156 0.12923668894236284 0.3933475002059922 0.20567492985288385 0.035475761429992765 0.11131357579382171 0.0942579899722151 0.018949063396727438 0.04186189287265346 0.03143404220589983 0.38967495698524895 0.22703975497278728 0.29741187239436967 0.23914057590796411 0.57804730404175 0.24171033789460433 0.17392240575138573 0.184603536676277 0.42761836399894787 0.39659817803115355 0.3751020913271997 0.01950307698100786 0.2625695183860021 0.22073592915143309 0.20582036517176783 0.369420147580965 0.2773013092965678 0.19943497692433002 0.3267906941685659 0.005820241331203462 0.16311505705839524 0.04913608749007178 0.01387569862008412 0.28520566913394546 0.14205073716575337 0.12277886300388204 0.6997266887237896 0.09483069917407068 0.2768585047169629 0.23199646449419403 0.3343962857015081 0.10320848016803578 0.4855466401802559 0.3681007141011737 0.16040665585343467 0.32895549491930975 0.2894424765141729 0.20773437577274823 0.1807537502002605 0.4831893686390465 0.11188212043354924 0.11832291401335175 0.38950842171129696 0.01899074809761161 0.2821208775142261 0.20281355273878107 0.16006231652850197 0.36885859527291315 0.6189772313227991 0.03510008672858905 0.29469664254967204 0.17945106059109525 0.2981410581447325 0.16280503593488763 0.3039072684825036 0.41406354203915047 0.11490753678710512 0.4156581161765415 0.42157259722738133 0.30477602485422256 0.3151917123591973 0.3374220165809283 0.4700533124361077 0.35596932660614927 0.04673407044316365 0.5637995950255145 0.3291321600915844 0.2466218362911997 0.31748470533613876 0.30006423753726064 0.35528253476143884 0.1748597210560712 0.08923746138536602 0.3447339299407352 0.3268400188580497 0.5423159413928924 0.27807172150319825 0.1869626032062326 0.14228266919701238 0.13657310937452125 0.12562513554359306 0.166966776077931 0.5206115475427028 0.5738483989466954 0.1615091986858126 0.2369338487604145 0.20602242128862144 0.27877820209217447 0.2993108768493067 0.18497408143658725 0.2817509492080923 0.33166797540202253 0.18284373610333257 0.022346363962270157 0.34921713783330777 0.14320119382417124 0.1170301771663153 0.08463899275859342 0.5474146809756119 0.25146713243443175 0.16907564583750304 0.22080668803168116 0.45870441712650284 );
}

relation X2 D1 D2 X3 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.31024060926052377 0.10387679541202773 0.15014694143755883 0.69655044401873 0.24006590873110267 0.5964542642380415 0.9095623927859997 0.03451111800396672 0.8643575094382288 0.5143957857308807 0.10848587975123147 0.3211751231893158 0.49136603727838196 0.5041294804529284 0.5032474206150399 0.3117871184826464 0.3181031940717565 0.527960883887684 0.1040102734841547 0.3104481964270303 0.47777715853185376 0.9333611204813723 0.7004240768082186 0.81928044613651 0.5409323175488682 0.44136896086459804 0.5534626456707264 0.027561775832520445 0.5259762683079106 0.5120097240859237 0.7611509993700016 0.7998034634015067 0.04953684308209113 0.06637945363935645 0.11886549701575748 0.374043787871949 0.5152096223428096 0.28568814003331877 0.6190585707379563 0.5635254646528628 0.10363077084639084 0.4197414537574743 0.7692314912374435 0.3062419403048843 0.9498792324896365 0.2550241970636503 0.5654007315441916 0.6132682509209021 0.6897593907394761 0.8961232045879723 0.8498530585624412 0.30344955598127 0.7599340912688973 0.40354573576195857 0.09043760721400021 0.9654888819960332 0.13564249056177122 0.4856042142691194 0.8915141202487685 0.6788248768106842 0.508633962721618 0.4958705195470715 0.4967525793849601 0.6882128815173536 0.6818968059282435 0.47203911611231597 0.8959897265158453 0.6895518035729696 0.5222228414681461 0.06663887951862772 0.29957592319178145 0.18071955386349 0.4590676824511319 0.558631039135402 0.4465373543292736 0.9724382241674796 0.4740237316920895 0.4879902759140763 0.2388490006299983 0.20019653659849326 0.9504631569179088 0.9336205463606436 0.8811345029842426 0.625956212128051 0.48479037765719024 0.7143118599666813 0.3809414292620436 0.4364745353471371 0.8963692291536092 0.5802585462425256 0.23076850876255647 0.6937580596951156 0.05012076751036341 0.7449758029363497 0.43459926845580843 0.38673174907909796 );
}

relation X3 D1 D2 X12 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3705324134883562 0.34341209074789253 0.4758235513655134 0.06830808285733592 0.33524112756583574 0.3166488105723625 0.050226177075496016 0.26487031057980676 0.3204474622314218 0.25223722561928313 0.6973914686431417 0.4913545587660857 0.6140144361368135 0.3933924131232009 0.44169619044135877 0.6499462846404372 0.08700765224330179 0.4338338006098649 0.44743847526556296 0.295147093541339 0.3069173996536537 0.4606573033416018 0.47306518986774004 0.23527157901310042 0.18491124865382383 0.39182039520258943 0.40397491034777805 0.2788366831619425 0.7354969616996548 0.18026781870446967 0.2763277336589568 0.39552087186967594 0.010999019224194849 0.6842981522715017 0.5177005204759049 0.28061488163986054 0.07204605952105295 0.22866581589704035 0.32023005231574364 0.38184223105512033 0.39367566890758887 0.19282525792948366 0.2981278769877626 0.1998291014265243 0.24888550582576038 0.37475317035057193 0.09936109684191655 0.3720843988048566 0.4570891999759405 0.46264592681851047 0.23784929254938306 0.36437036689493907 0.32973475097801946 0.021853152837132423 0.4307035390928476 0.3983127198192739 0.2917578414665601 0.2525807412626954 0.718378322577513 0.19880058820494761 0.1587707808469636 0.2960856055782132 0.22236701622642988 0.07522403740798926 0.31941180181907974 0.3793796813020867 0.45730471465463063 0.5519184520360448 0.45436938002627075 0.18366356791759228 0.9006567089094787 0.16876306431662605 0.4902959692105827 0.3391762664531026 0.156148016845788 0.5328735699237098 0.17748505815061638 0.7917379632493841 0.031247909790369222 0.3753454030139541 0.5008086545389974 0.7614082488121787 0.32656233333167095 0.25582135678655493 0.579761056629291 0.35668199627165476 0.13098822379511724 0.11127706731761204 0.18651455018986815 0.1294861880488365 0.2048298423955516 0.3357972699204074 0.5866064751097388 0.005869706264100057 0.5584120271410344 0.14786256629011282 0.620360741805544 0.3525842102165954 0.4576907609512373 0.5654488816020742 0.09815144868403404 0.7175070035635978 0.3628195305025834 0.046566254326699766 0.3710311094315489 0.09399476901079869 0.4274971782823126 0.42443765683832335 0.46048115376986254 0.9266617778672411 0.02544257711362213 0.08245954157674223 0.205795891160297 0.17592320607700315 0.0037516652956859574 0.28578078882791186 0.3154563934407079 0.3847392323450751 0.8280909695810683 0.4206042302745184 0.22781631014906745 0.04920772305055219 0.2811054756140959 0.34645023999567476 0.18683596557929025 0.4507685487032279 0.157799231753449 0.3442863430705437 0.563278088002949 0.25128567308121347 0.214878015498728 0.51158335457406 0.43454537349470324 0.031239327799350583 0.35671544768782965 0.14830380876847513 0.22732387322500422 0.43780476079846736 0.544776948865238 0.2906645350353347 0.26584762534360057 0.29912743324091684 0.19738264387832707 0.2678377075864931 0.5755944594393759 0.37150376038170924 0.3949287978393971 0.507170730868915 0.2732346927528054 0.6055170245505644 0.5577629988028496 0.36124803538818584 0.09526272617658837 0.3704393217493332 0.23701380151055904 0.04278911219315357 0.21815853989407033 0.3030977786480997 0.07493360882602822 0.2808112248962677 0.5792889876757237 0.4109491342213345 0.49609537065272913 0.12451808471276239 0.4914889160381802 0.22082394631460142 0.09216592065012895 0.40031328433718505 0.6566142165972833 0.5452532115586339 0.4965756609576132 0.3766172828950625 0.04514257176805354 0.37501053834970377 0.263425386575513 0.571987513463107 0.5213406423745288 0.08778687296268854 0.2204196377336194 0.38566082135949503 0.406093607070407 0.29833450019751456 0.4993595702532177 0.23784084453574245 0.27625310750572546 0.09982584520329793 0.47769406507411705 0.11469598734778443 0.6399421534427151 0.2494737523644051 0.3240873556707191 0.5339003675352767 0.2769612702600779 0.3112644722614123 0.4568575457329912 0.4891256406249443 0.331256862260878 0.22880148358458424 0.3257254976473355 0.26888222700151065 0.29291624701282265 0.25316443495980834 0.18459671962730048 0.7800946241558651 0.2900418696542405 0.3050755832340572 0.4030211867991457 0.3539115899841805 0.355463197541876 0.49526804997262974 0.4242705938362165 0.15867250811672295 0.4642711921226019 0.40444389493614025 0.09627991018624386 0.3758667870901492 0.2842996536088467 0.0967781439659874 0.7642261053482557 0.37690452932025775 0.3965319609114438 0.010313646615598865 0.5253864757319251 0.5238665665007182 0.4299578456119176 0.1986717542046818 0.300026235418309 0.6390973408519008 0.1594650075776658 0.38318847216757607 0.6555529863937493 0.690170744858756 0.34755390612655124 0.4269816152261804 0.33810729114080906 0.2647959963759528 0.07837183609822021 0.7295056327036086 0.37677368257929944 0.3320352562645613 0.19203769893567613 0.17004130720810265 0.12475776264401314 0.02980631796814945 0.4447801176650494 0.04395325607823448 0.22185833372270086 0.8522778927276438 0.2574956634043991 0.25871395041041856 0.10235793234494797 0.388887628357182 0.1420966092081053 0.003912266081639117 0.42015531639997067 0.37034648492140676 0.4179089831063666 0.5078890798010494 0.33300799352510085 0.4533931495432492 0.18429831620187506 0.2812437547333186 0.01557387445403434 0.11971182492412581 0.3106271765393367 0.12459688493171808 0.3340386532783088 0.15696025663739516 0.4088095383458748 0.222417139346917 0.23474183035112542 0.3373243725483382 0.09232005161803494 0.4822916477901226 0.4000108340748894 0.22908444454977486 0.35110668282167534 0.5999621648320287 0.2958791906928013 0.47306772416827364 0.5708423464361466 0.3990931352093296 0.1464212533640009 0.20435628069671477 0.6497448963200179 0.23437658557555122 0.6332919486793653 0.46625139477352456 0.053873127072267823 0.2850841488703983 0.1292476507950895 0.42452118627374913 0.39152417968135883 0.07783416487707298 0.3920108241216543 0.37388165403200746 0.5842898115919897 0.3773234526313838 0.06559472984629917 0.46585632904076074 0.16782702396911625 0.30350980822869944 0.483370200732613 0.0692424904632952 0.3337033600809744 0.15521706516880063 0.05646615408170789 0.5803348217458985 0.20159368430816613 0.3185187503437968 0.43476888948213105 0.3644151366497144 0.1584745347488928 0.06292639323877663 0.09944942869460865 0.34454603394299504 0.2193604665322917 0.4447216429458265 0.46024687976553025 0.03249161466721724 0.4676603384012764 0.2279149747658097 0.2618798417904757 0.3337242970006444 0.52186033340854 0.4729996839054451 0.18041037743103872 0.3803169244091371 0.33007122358668567 0.7073488968672185 0.22417805793812037 0.6854749112256913 0.11117234073152468 0.3757730772850231 0.5765515474873643 0.09401523365986669 0.26594952976398156 0.2260896009200771 0.3052931617176256 0.1465039924801167 0.33900838676110256 0.7493453635782834 0.243570963259817 0.33280505317921544 0.41532591152061726 0.4942548237774961 0.09702495779518654 0.021104787639187442 0.5511873494987958 0.39883881118772974 0.37461179697442437 0.5708643726078303 0.32512500063904426 0.12535226872528352 0.11842469150915293 0.2894090398472322 0.08135942785112725 0.41189253714626756 0.0030633809042773467 0.4553701485932248 0.22540437718057066 0.5640455895809099 0.07962587780595609 0.0902219007560325 0.42598298093793974 0.1979483901350171 0.4433656144777056 0.10078803048532761 0.0692334998490849 0.039919996983139486 0.3734114312500201 0.10508130236154425 0.26077393579304314 0.26012953156076923 0.21345878981113345 0.1985521878236321 0.46593154368358053 0.44353219672498306 0.45706286646363936 0.3994067337036398 0.335021688792041 0.2646246610322914 0.06481429027966613 0.5201021774453259 0.18760155925877997 0.47737448257530196 0.4175514764047495 0.40474480042977634 0.4570684336509166 0.2385397403581677 0.41532213577471566 0.10115585294565654 0.371473227164052 0.6472912805787828 0.47014488937273935 0.18667471480449468 0.39742223702203205 0.06942595605111981 0.5544021064864072 0.547193973501851 0.3762951257333363 0.31618771412194735 0.6632403411792133 0.2608260616288389 0.500245290357417 0.3340170129216062 0.15633515596489742 0.4596839448013558 0.46155651331159586 0.8261953920177297 0.38485587110759545 0.4965895033669302 0.4043544960748349 0.32681431194985505 0.6074589378954256 0.31838928438111813 0.34440186037901593 0.2664226791286639 0.3851111504263825 0.25933220087616504 0.2143479436836214 0.3687985073686207 0.34740536161936914 0.3786284670632512 0.20183378033884905 0.16310210399220318 0.30880179777076117 0.5049791842679505 0.08440747833638168 0.466495981183532 0.16932540744230767 0.2659108976399824 );
}

relation X4 D1 X3 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.27247932845642453 0.1646339756107944 0.2937670078436169 0.6269984039127057 0.29444349025821503 0.32729177279181554 0.27409921770692924 0.20624507600448871 0.6299003285999111 0.24371741362460972 0.030758532587065457 0.2816686796543547 0.3540277543932528 0.24362858502030427 0.4430417567117367 0.3134177764152033 0.2845336000243963 0.0959874974361565 0.5715473196448686 0.4851234528799302 0.2030177230447213 0.4145618620223676 0.43576104263734583 0.025308000002386286 0.3734929171503227 0.5917374393689014 0.2631912354446464 0.0595838196720911 0.42102290971738876 0.5767207297720279 0.15435346264820216 0.3086314711155811 0.16708194835536766 0.34172072435302264 0.5334804247755888 0.693023320343259 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (98.25345628326195 85.04569422440571 99.34895890306024 126.38962322882287 128.73101134610857 115.52324928725236 129.82651396590688 156.8671782916695 119.35408623485273 106.14632417599651 120.44958885465104 147.49025318041365 79.41857076043644 66.21080870158022 80.51407338023475 107.55473770599735 );
}

}