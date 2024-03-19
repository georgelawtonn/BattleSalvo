- Renamed the PA03 BattleSalvoController methods, slightly modified call order *Has no effect on functionality, or overarching design* 
    This change was made purely to improve the readability of the local play, as stated before it has no effect on functionality, or overarching design.
- AiPlayer modified to have two game modes to support both server and local play
    Added isLocal field to determine the functionality of certain methods, so that local and server play are supported
- Added json creator to coord class
    Added to allow Json creation of coord
- Changed main method to support both modes of BattleSalvo
    Changed to allow for both forms of play (local/server)
