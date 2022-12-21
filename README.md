# SMPChat
 A chat-oriented plugin designed for the OwO SMP
 
 Heavily inspired by https://github.com/NerdNu/NerdMessage
 
 All messages and colours of those messages are modifyable in the config.yml. Colours are in hex code format.

## Commands
Sends private message to player
- `/msg [player] [message]`
- `/message [player] [message]`
- `/whisper [player] [message]`
- `/tell [player] [message]`

Chat Colour
- `/chatcolour [#FFFFFF], /chatcolor [#FFFFFF]` Changes chat colour to hex code specified.

Moderation
- `/mmsg [player] [message]` Sends a message in green moderator mode to player.
- `/bc [message], /broadcast [message]` Sends message to all players on the server in green.

## Permissions
| Command                 | Permission         |
| :---------------------: | :----------------: |
| /msg, /tell, /whisper   | smpchat.msg        |
| /chatcolor, /chatcolour | smpchat.chatcolour |
| /mmsg                   | smpchat.mod        |
| /broadcast              | smpchat.mod        |
