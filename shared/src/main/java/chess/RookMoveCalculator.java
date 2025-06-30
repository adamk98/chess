package chess;

import java.util.ArrayList;
import java.util.Collection;

//class
public class RookMoveCalculator implements PieceMovesCalculator {
    private final ChessGame.TeamColor teamColor;

    //constructor
    public RookMoveCalculator(ChessGame.TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    //i need to
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        // Rooks may move in straight lines as far as there is open space. If there is an
        // enemy piece at the end of the line, rooks may move to that position and capture
        // the enemy piece
        Collection<ChessMove> theMoves = new ArrayList<>();

        //myPosition gives row and col
        //within bounds of baord. getCol in ChessPosition
        if(myPosition.getColumn() > 8 || myPosition.getColumn() < 1){
            return null;
        }
        if(myPosition.getRow() > 8 || myPosition.getRow() < 1){
            return null;

        }

        //create my piece. rook is stored locally right here as myPiece
        ChessPiece myPiece = board.getPiece(myPosition);

        //caclulate create new position
        ChessPosition testPosition = new ChessPosition(myPosition.getRow(), myPosition.getColumn() + 1);
        //test position if it is off the board
        if(testPosition.getColumn() > 8 || testPosition.getColumn() < 1){
            return null;
        }
        if(testPosition.getRow() > 8 || testPosition.getRow() < 1){
            return null;
        }

        //chessboard stores position of pieces from ChessPosition
        //isPiece an instance of ChessPiece(contains the color and type of piece)is the piece in the square I am looking at.
        //
        ChessPiece isPiece = board.getPiece(testPosition);
        //if space on board is empty, that's a possible move, so add it to collection
        if(isPiece == null){
            theMoves.add(new ChessMove(myPosition, testPosition, null));
        //i
        } else if(isPiece.getTeamColor() == myPiece.getTeamColor()) {

        }


        return new ArrayList<>();
    }
}

