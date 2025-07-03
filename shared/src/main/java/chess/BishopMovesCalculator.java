package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BishopMovesCalculator implements PieceMovesCalculator {
    private final ChessGame.TeamColor teamColor;

    //Constructor
    public BishopMovesCalculator(ChessGame.TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    //What does override do?
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        //yeah I am going to make my own unique collection of moves for my bishop. so even though
        //I made a theMoves collection of ArrayList in rook move calc I am going to make another with the same name in
        // this method. and I can because of scope.
        Collection<ChessMove> bishopMoves = new ArrayList<>();

        //this tests all four directions bishop piece can move
        bishopMoves.addAll(repeatMoves(board, myPosition, 1, 1));
        bishopMoves.addAll(repeatMoves(board, myPosition, 1, -1));
        bishopMoves.addAll(repeatMoves(board, myPosition, -1, -1));
        bishopMoves.addAll(repeatMoves(board, myPosition, -1, 1));

        //this needs to return theMoves
        return bishopMoves;
    }

    private Collection<ChessMove> repeatMoves(ChessBoard board, ChessPosition myPosition, int rowMod, int colMod){

        Collection<ChessMove> bishopMoves = new ArrayList<>();
        //make collection that stores possible moves. check. make while loop so no moves can happen
        //outside the length of the board. inside the length of the board(the while loop) check conditions, like
        //if nothing is there it is possible move. if it runs into an opposing teams piece(teamColor) then that lo
        //location of the enemy spot is a possible move. runs into friendly piece. return bishop moves so I can call
        //this method and it'll give me all the possible moves of bishop piece.

        ChessPiece myPiece = board.getPiece(myPosition);
        //put in while loop getColumn and getRow.
        //make a testPosition that is one step ahead of myPosition
        ChessPosition testPosition = new ChessPosition(myPosition.getRow() + rowMod, myPosition.getColumn() + colMod);
        ChessPiece isPiece;

        while(testPosition.getColumn() <= 8 && testPosition.getColumn() >= 1 && testPosition.getRow() <= 8 && testPosition.getRow() >= 1) {
            //check if empty space
            isPiece = board.getPiece(testPosition);
            if(isPiece == null){
                //add the location of isPiece to bishopMoves
                bishopMoves.add(new ChessMove(myPosition, testPosition, null));
                //check if mypeice and ispiece share the same color. if not then I can take that space.
                testPosition = new ChessPosition(testPosition.getRow() + rowMod, testPosition.getColumn() + colMod);
            }else if(isPiece.getTeamColor() != myPiece.getTeamColor()){
                bishopMoves.add(new ChessMove(myPosition, testPosition, null));
                break;
                //check if same color. so that's anything else. if same color don't do anything so just return
            }else{
                break;
            }
        }
        return bishopMoves;
    }
}
