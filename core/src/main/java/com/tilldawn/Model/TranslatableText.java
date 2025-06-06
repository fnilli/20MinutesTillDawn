package com.tilldawn.Model;

public enum TranslatableText {
    //login menu
    LoginMenuTitle("L o g i n   M e n u", "Menu de connexion"),
    LoginButton("Login", "se connecter"),
    ForgotPassword("Forgot Password?", "Mot de passe oublié?"),
    SecurityAnswer("Your security answer", "Votre réponse de sécurité"),
    Username("Username", "nom d'utilisateur"),
    Password("Password", "mot de passe"),


    // profile menu
    ProfileMenuTitle("P r o f i l e    M e n u", "Menu de profil"),
    ChangeUsername("Change Username", "Changer le nom d'utilisateur"),
    ChangePassword("Change Password", "Changer le mot de passe"),
    DeleteAccount("Delete Account", "Supprimer le compte"),
    ChooseAvatar("Choose Avatar", "Choisir un avatar"),
    DragAndDrop("Drag & Drop", "Glisser-Déposer"),
    EmptyUsernameWarning("Username cannot be empty.", "Le nom d'utilisateur ne peut pas être vide."),
    EmptyPasswordWarning("Password cannot be empty.", "Le mot de passe ne peut pas être vide."),
    AvatarUpdated("avatar photo updated", "photo de l'avatar mise à jour"),

    // Main menu
    MainMenuTitle("M a i n    M e n u", "M e n u    P r i n c i p a l"),
    LoadGame("Load Game", "Charger la partie"),
    Settings("Settings", "Paramètres"),
    Profile("Profile", "Profil"),
    PreGame("Pre-Game", "Préparation"),
    Scoreboard("Scoreboard", "Classement"),
    Talent("Talent", "Talents"),
    Logout("Logout", "Déconnexion"),
    UsernameLabel("Username: ", "Nom d'utilisateur : "),
    ScoreLabel("Score: ", "Score : "),


    // Settings menu
    SettingMenuTitle("S e t t i n g", "P a r a m è t r e s"),
    MusicVolume("Music Volume", "Volume de la musique"),
    MusicTrack("Music Track", "Piste musicale"),
    SoundEffects("Sound Effects", "Effets sonores"),
    AutoReload("Auto Reload", "Rechargement auto"),
    DarkTheme("Dark B/W Theme", "Thème noir et blanc"),
    Controls("Controls", "Contrôles"),
    MoveUp("Move Up", "Haut"),
    MoveDown("Move Down", "Bas"),
    MoveLeft("Move Left", "Gauche"),
    MoveRight("Move Right", "Droite"),
    SelectLanguage("Select Language", "Choisir la langue"),
    BackToMainMenu("Back to Main Menu", "Retour au menu principal"),

    // Pause menu
    PauseMenuTitle("Paused", "Pause"),
    CheatCodesTitle("Cheat Codes", "Codes de triche"),
    AchievedAbilitiesTitle("Achieved Abilities", "Compétences acquises"),
    ContinueButton("Continue", "Continuer"),
    GiveUpButton("Give Up", "Abandonner"),
    NoAbilitiesMessage("No abilities acquired yet.", "Aucune compétence acquise."),
    CheatCodeInstructions(
        "Cheats:\n- M key: -60 seconds\n- N key: Level up\n- H key: Increase heath\n- J key: Invite winged monster\n- K key: +10 ammos",
        "Triches :\n- Touche M : -60 secondes\n- Touche N : Monter de niveau\n- Touche H : Augmenter la santé\n- Touche J : Invoquer un monstre ailé\n- Touche K : +10 munitions"
    ),


    // Pre-Game Menu
    PreGameMenuTitle("P r e  G a m e    M e n u", "M e n u   d' a v a n t  -   j e u"),
    BackButton("Back", "Retour"),
    StartGameButton("Start Game", "Commencer le jeu"),
    SelectGameTimeLabel("Select Game Time:", "Sélectionner la durée de jeu :"),
    WeaponSetToRevolver("weapon set to Revolver", "arme définie sur Revolver"),
    WeaponSetToShotgun("weapon set to Shotgun", "arme définie sur Fusil à pompe"),
    WeaponSetToSmg("weapon set to Smg", "arme définie sur Pistolet-mitrailleur"),
    GameTimeOption2("2 minutes", "2 minutes"),
    GameTimeOption5("5 minutes", "5 minutes"),
    GameTimeOption10("10 minutes", "10 minutes"),
    GameTimeOption20("20 minutes", "20 minutes"),


    //Scoreboard
    ScoreboardSortBy("Sort by:", "Trier par :"),
    ScoreboardSortScore("Score", "Score"),
    ScoreboardSortUsername("Username", "Nom d'utilisateur"),
    ScoreboardSortKills("Kills", "Éliminations"),
    ScoreboardSortSurviveTime("Survive Time", "Temps de survie"),
    ScoreboardBack("Back", "Retour"),

    // Talent Menu
    TalentMenuTitle("Talent (Hint) Menu", "Menu de talents (conseils)"),
    HeroHints("Hero Hints", "Conseils pour les héros"),
    KeyBindings("Key Bindings", "Raccourcis clavier"),
    CheatCodes("Cheat Codes", "Codes de triche"),
    Abilities("Abilities", "Compétences"),
    HeroHintsDescription(
        "Hero Tips:\n- Shanna: Beautiful balanced hero.\n- Diamond: God of health.\n- Dasher: The fastest hero.",
        "Conseils héros :\n- Shanna : Héroïne équilibrée.\n- Diamond : Dieu de la santé.\n- Dasher : Le plus rapide."
    ),
    KeyBindingsDescription(
        "Current Key Bindings:\n- Move: W/A/S/D\n- Attack: mouse click",
        "Contrôles actuels :\n- Déplacement : W/A/S/D\n- Attaque : clic souris"
    ),
    AbilitiesDescription(
        "Vitality,      +1 max HP\n" +
            "Damager,       +25% damage for 10s\n" +
            "Procrease,     +1 projectile count\n" +
            "Amocrease,     +5 max ammo\n" +
            "Speedy,         x2 movement speed for 10s",
        "Vitalité,        +1 PV max\n" +
            "Dégâts+,        +25% dégâts pendant 10s\n" +
            "Projectiles+,   +1 projectile\n" +
            "Munitions+,     +5 munitions max\n" +
            "Rapide,         x2 vitesse pendant 10s"
    ),


    Submit("Submit", "Soumettre"),
    Back("Back", "dos"),
    PlayButton("Play", "Jouer"),
    SettingsButton("Settings", "Paramètres"),
    ExitButton("Exit", "Quitter");

    private final String english;
    private final String french;

    TranslatableText(String english, String french) {
        this.english = english;
        this.french = french;
    }

    public String getText(String language) {
        switch (language) {
            case "English" : return english;
            case "French" : return french;
            default : return english;
        }
    }

    public String getText() {
        if (App.getCurrentPlayer() == null){
            return getText(english);
        }else{
            return getText(App.getCurrentPlayer().getLanguage());

        }
    }
}
